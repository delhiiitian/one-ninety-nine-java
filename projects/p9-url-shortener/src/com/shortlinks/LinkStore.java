package com.shortlinks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Clock;
import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Thread-safe storage for links. Many requests arrive at once, so maps are concurrent
 * and click counters are atomic (Chapter 9.4). Links are appended to a CSV file (Chapter 6).
 */
public class LinkStore {
    private static final Logger LOG = Logger.getLogger(LinkStore.class.getName());
    private static final Pattern VALID_URL = Pattern.compile("^https?://[\\w.-]+(:\\d+)?(/\\S*)?$");   // Chapter 9.3

    private final Map<String, Link> links = new ConcurrentHashMap<>();
    private final Map<String, AtomicLong> clicks = new ConcurrentHashMap<>();
    private final Map<String, String> codeByUrl = new ConcurrentHashMap<>();
    private final CodeGenerator generator;
    private final Path file;
    private final Clock clock;

    public LinkStore(Path file, CodeGenerator generator, Clock clock) throws IOException {
        this.file = file;
        this.generator = generator;
        this.clock = clock;
        load();
    }

    public static boolean isValidUrl(String url) {
        return url != null && url.length() <= 2048 && VALID_URL.matcher(url).matches();
    }

    /** Same URL twice returns the same code. synchronized: two threads must not create two codes for one URL. */
    public synchronized Link shorten(String url) throws IOException {
        if (!isValidUrl(url)) throw new IllegalArgumentException("Not a valid http(s) URL: " + url);
        String existing = codeByUrl.get(url);
        if (existing != null) return links.get(existing);
        String code;
        do {
            code = generator.next();
        } while (links.containsKey(code));            // collisions are rare, but handled
        Link link = new Link(code, url, Instant.now(clock));
        put(link);
        Files.writeString(file, link.toCsv() + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        LOG.fine("Created " + code + " -> " + url);
        return link;
    }

    /** Looks up a code and counts the visit. */
    public Optional<Link> resolve(String code) {
        Link link = links.get(code);
        if (link != null) clicks.get(code).incrementAndGet();
        return Optional.ofNullable(link);
    }

    public Optional<Link> find(String code) { return Optional.ofNullable(links.get(code)); }
    public long clicks(String code) { return clicks.getOrDefault(code, new AtomicLong()).get(); }
    public Collection<Link> all() { return links.values(); }

    private void put(Link link) {
        links.put(link.code(), link);
        clicks.putIfAbsent(link.code(), new AtomicLong());
        codeByUrl.put(link.targetUrl(), link.code());
    }

    private void load() throws IOException {
        if (file.getParent() != null) Files.createDirectories(file.getParent());
        if (!Files.exists(file)) return;
        for (String line : Files.readAllLines(file)) {
            if (line.isBlank()) continue;
            try {
                put(Link.fromCsv(line));
            } catch (RuntimeException e) {
                LOG.warning("Skipping corrupt line: " + line);
            }
        }
        LOG.info("Loaded " + links.size() + " links from " + file);
    }
}
