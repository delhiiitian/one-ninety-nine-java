package com.shortlinks;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * The HTTP layer (Chapter 9.7). Routes:
 *   POST /api/links          body {"url":"..."}  -> 201 + JSON
 *   GET  /api/links          -> all links with click counts
 *   GET  /api/links/{code}   -> one link's stats
 *   GET  /{code}             -> 302 redirect to the long URL
 */
public class ShortenerServer {
    private static final Logger LOG = Logger.getLogger(ShortenerServer.class.getName());
    private static final DateTimeFormatter TIME = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneOffset.UTC);

    private final LinkStore store;
    private final HttpServer server;
    private final String baseUrl;

    public ShortenerServer(LinkStore store, int port) throws IOException {
        this.store = store;
        this.server = HttpServer.create(new InetSocketAddress("localhost", port), 0);
        this.server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());   // one cheap thread per request
        this.server.createContext("/api/links", this::api);
        this.server.createContext("/", this::redirect);
        this.baseUrl = "http://localhost:" + server.getAddress().getPort() + "/";
    }

    public void start() { server.start(); }
    public void stop() { server.stop(0); }
    public String baseUrl() { return baseUrl; }

    private void api(HttpExchange ex) throws IOException {
        try {
            String path = ex.getRequestURI().getPath();
            String method = ex.getRequestMethod();
            if (path.equals("/api/links") && method.equals("POST")) {
                String url = Json.stringField(readBody(ex), "url");
                if (url == null) {
                    send(ex, 400, Json.error("Body must be JSON like {\"url\": \"https://...\"}"));
                    return;
                }
                try {
                    Link link = store.shorten(url);
                    send(ex, 201, linkJson(link));
                } catch (IllegalArgumentException e) {
                    send(ex, 400, Json.error(e.getMessage()));
                }
            } else if (path.equals("/api/links") && method.equals("GET")) {
                String list = store.all().stream()
                        .sorted(Comparator.comparing(Link::createdAt).thenComparing(Link::code))
                        .map(this::linkJson)
                        .collect(Collectors.joining(",", "[", "]"));
                send(ex, 200, list);
            } else if (path.startsWith("/api/links/") && method.equals("GET")) {
                String code = path.substring("/api/links/".length());
                store.find(code).ifPresentOrElse(
                        link -> sendQuietly(ex, 200, linkJson(link)),
                        () -> sendQuietly(ex, 404, Json.error("No link " + code)));
            } else {
                send(ex, 405, Json.error("Method not allowed"));
            }
        } catch (RuntimeException e) {
            LOG.log(Level.SEVERE, "Unexpected error", e);
            send(ex, 500, Json.error("Internal error"));
        }
    }

    private void redirect(HttpExchange ex) throws IOException {
        String code = ex.getRequestURI().getPath().substring(1);
        if (code.isEmpty()) {
            send(ex, 200, "{\"service\":\"Java 199 URL shortener\",\"links\":" + store.all().size() + "}");
            return;
        }
        var link = store.resolve(code);
        if (link.isPresent()) {
            ex.getResponseHeaders().set("Location", link.get().targetUrl());
            ex.sendResponseHeaders(302, -1);       // -1 = no body
            ex.close();
        } else {
            send(ex, 404, Json.error("No link " + code));
        }
    }

    private String linkJson(Link l) {
        return "{\"code\":" + Json.quote(l.code())
                + ",\"shortUrl\":" + Json.quote(baseUrl + l.code())
                + ",\"url\":" + Json.quote(l.targetUrl())
                + ",\"createdAt\":" + Json.quote(TIME.format(l.createdAt()))
                + ",\"clicks\":" + store.clicks(l.code()) + "}";
    }

    private static String readBody(HttpExchange ex) throws IOException {
        try (InputStream in = ex.getRequestBody()) {
            return new String(in.readNBytes(10_000), StandardCharsets.UTF_8);    // cap the size we accept
        }
    }

    private static void send(HttpExchange ex, int status, String json) throws IOException {
        byte[] body = json.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        ex.sendResponseHeaders(status, body.length);
        try (var out = ex.getResponseBody()) {
            out.write(body);
        }
    }

    private static void sendQuietly(HttpExchange ex, int status, String json) {
        try {
            send(ex, status, json);
        } catch (IOException e) {
            LOG.warning("Client went away: " + e.getMessage());
        }
    }
}
