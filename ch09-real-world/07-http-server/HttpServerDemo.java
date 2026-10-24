// 9.7 Building a small web server
// Run: java HttpServerDemo.java          (self-test: starts, calls itself, stops)
//      java HttpServerDemo.java --serve  (keeps running: open http://localhost:8080/hello?name=Ana)
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpServerDemo {

    static final AtomicInteger hits = new AtomicInteger();

    public static void main(String[] args) throws Exception {
        boolean serve = args.length > 0 && args[0].equals("--serve");
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", serve ? 8080 : 0), 0);

        // A context maps a URL path to a handler, like a receptionist routing visitors to rooms
        server.createContext("/hello", HttpServerDemo::hello);
        server.createContext("/stats", ex -> send(ex, 200, "text/plain", "Requests served: " + hits.get()));
        server.start();
        int port = server.getAddress().getPort();

        if (serve) {
            System.out.println("Listening on http://localhost:" + port + "/hello?name=You  (Ctrl+C to stop)");
            return;                                  // the server thread keeps the program alive
        }

        // Self-test: be our own first visitor
        HttpClient client = HttpClient.newHttpClient();
        for (String path : new String[]{"/hello?name=Ana", "/hello", "/stats"}) {
            HttpResponse<String> r = client.send(
                    HttpRequest.newBuilder(URI.create("http://localhost:" + port + path)).build(),
                    HttpResponse.BodyHandlers.ofString());
            System.out.println("GET " + path + " -> " + r.statusCode() + " " + r.body());
        }
        server.stop(0);
    }

    static void hello(HttpExchange ex) throws IOException {
        hits.incrementAndGet();
        String query = ex.getRequestURI().getQuery();          // "name=Ana" or null
        String name = (query != null && query.startsWith("name=")) ? query.substring(5) : "stranger";
        send(ex, 200, "application/json", "{\"message\":\"Hello, " + name + "!\"}");
    }

    static void send(HttpExchange ex, int status, String type, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", type);
        ex.sendResponseHeaders(status, bytes.length);
        try (var out = ex.getResponseBody()) {
            out.write(bytes);
        }
    }
}
