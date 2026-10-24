package com.shortlinks;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Project 9: URL Shortener web service.
 *   java -cp out com.shortlinks.Main --serve   keeps running on $PORT (default 8080)
 *   java -cp out com.shortlinks.Main --demo    starts on a free port, acts as its own client, then stops
 * Data is stored in $DATA_FILE (default data/links.csv).
 */
public class Main {

    public static void main(String[] args) throws Exception {
        boolean demo = args.length > 0 && args[0].equals("--demo");
        Logger.getLogger("").setLevel(demo ? Level.WARNING : Level.INFO);

        Path dataFile = Path.of(System.getenv().getOrDefault("DATA_FILE", "data/links.csv"));
        int port = demo ? 0 : Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        LinkStore store = new LinkStore(dataFile, new CodeGenerator(6, new SecureRandom()), Clock.systemUTC());
        ShortenerServer server = new ShortenerServer(store, port);
        server.start();

        if (!demo) {
            System.out.println("URL shortener running at " + server.baseUrl());
            System.out.println("Try: curl -X POST " + server.baseUrl() + "api/links -d '{\"url\":\"https://openjdk.org\"}'");
            Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
            return;
        }
        runDemo(server.baseUrl());
        server.stop();
    }

    static void runDemo(String base) throws Exception {
        HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NEVER).build();

        // 1. Shorten a link
        HttpResponse<String> created = post(client, base + "api/links", "{\"url\":\"https://docs.oracle.com/en/java/\"}");
        System.out.println("POST valid URL   -> " + created.statusCode());
        String code = Json.stringField(created.body(), "code");

        // 2. Same URL again gives the same code
        String again = Json.stringField(post(client, base + "api/links", "{\"url\":\"https://docs.oracle.com/en/java/\"}").body(), "code");
        System.out.println("Same URL, same code? " + code.equals(again));

        // 3. Bad input is rejected with 400
        System.out.println("POST bad URL     -> " + post(client, base + "api/links", "{\"url\":\"not a url\"}").statusCode());
        System.out.println("POST no url      -> " + post(client, base + "api/links", "{}").statusCode());

        // 4. Fifty visitors click the link at the same moment
        List<CompletableFuture<HttpResponse<Void>>> visits = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            visits.add(client.sendAsync(HttpRequest.newBuilder(URI.create(base + code)).build(), HttpResponse.BodyHandlers.discarding()));
        }
        HttpResponse<Void> first = visits.get(0).join();
        CompletableFuture.allOf(visits.toArray(new CompletableFuture[0])).join();
        System.out.println("GET /" + "<code>      -> " + first.statusCode() + " Location: " + first.headers().firstValue("Location").orElse("?"));

        // 5. Stats show every click was counted, even under concurrency
        String stats = client.send(HttpRequest.newBuilder(URI.create(base + "api/links/" + code)).build(),
                HttpResponse.BodyHandlers.ofString()).body();
        System.out.println("Clicks counted: " + stats.replaceAll(".*\"clicks\":(\\d+).*", "$1"));

        System.out.println("GET unknown code -> " + client.send(HttpRequest.newBuilder(URI.create(base + "zzzzzz")).build(),
                HttpResponse.BodyHandlers.ofString()).statusCode());
    }

    static HttpResponse<String> post(HttpClient client, String url, String json) throws Exception {
        return client.send(HttpRequest.newBuilder(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json)).build(), HttpResponse.BodyHandlers.ofString());
    }
}
