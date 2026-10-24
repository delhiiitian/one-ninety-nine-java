// 9.6 Calling a web API with HttpClient
// Run: java HttpClientDemo.java
// To keep this example working offline, it first starts a tiny fake "weather API" on your machine.
// Swap the URL for any real API (for example https://api.github.com/users/octocat) to call the internet.
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class HttpClientDemo {
    public static void main(String[] args) throws Exception {
        HttpServer fakeApi = startFakeWeatherApi();
        int port = fakeApi.getAddress().getPort();

        // 1. One client, reused for every call (like keeping one phone for all your calls)
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        // 2. Describe the request: address, method, headers
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/weather?city=Pune"))
                .header("Accept", "application/json")
                .GET()
                .build();

        // 3. Send it and read the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + response.statusCode());
        System.out.println("Content-Type: " + response.headers().firstValue("Content-Type").orElse("?"));
        System.out.println("Body: " + response.body());

        // POST: sending data to the server
        HttpRequest post = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + "/feedback"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"rating\": 5}"))
                .build();
        HttpResponse<String> postResponse = client.send(post, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST status: " + postResponse.statusCode() + ", body: " + postResponse.body());

        // Always check the status code: 404 is a normal response, not an exception
        HttpRequest missing = HttpRequest.newBuilder(URI.create("http://localhost:" + port + "/nope")).build();
        System.out.println("Missing page status: " + client.send(missing, HttpResponse.BodyHandlers.ofString()).statusCode());

        fakeApi.stop(0);
    }

    static HttpServer startFakeWeatherApi() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);  // port 0 = any free port
        server.createContext("/weather", ex -> {
            String city = ex.getRequestURI().getQuery().replace("city=", "");
            byte[] body = ("{\"city\":\"" + city + "\",\"tempC\":29,\"sky\":\"clear\"}").getBytes(StandardCharsets.UTF_8);
            ex.getResponseHeaders().add("Content-Type", "application/json");
            ex.sendResponseHeaders(200, body.length);
            ex.getResponseBody().write(body);
            ex.close();
        });
        server.createContext("/feedback", ex -> {
            byte[] body = "{\"saved\":true}".getBytes(StandardCharsets.UTF_8);
            ex.sendResponseHeaders(ex.getRequestMethod().equals("POST") ? 201 : 405, body.length);
            ex.getResponseBody().write(body);
            ex.close();
        });
        server.createContext("/", ex -> {
            ex.sendResponseHeaders(404, -1);
            ex.close();
        });
        server.start();
        return server;
    }
}
