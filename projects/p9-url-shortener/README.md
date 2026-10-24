# Project 9: URL Shortener Web Service

A real JSON web service, like a mini bit.ly: create short links, redirect visitors, count clicks safely under heavy concurrent traffic, and keep links on disk across restarts. Only the JDK is needed.

```
p9-url-shortener/src/com/shortlinks/
├── Main.java             --serve (keep running) or --demo (self-test client)
├── ShortenerServer.java  HTTP routes, JSON responses, virtual-thread executor
├── LinkStore.java        concurrent maps, atomic click counters, CSV persistence, URL validation
├── CodeGenerator.java    6-character base-62 codes from SecureRandom
├── Link.java             record
└── Json.java             tiny JSON helpers
```

| Method | Path | Result |
|---|---|---|
| POST | `/api/links` body `{"url":"https://..."}` | 201 + link JSON (400 if invalid) |
| GET | `/api/links` | all links with click counts |
| GET | `/api/links/{code}` | one link's stats (404 if unknown) |
| GET | `/{code}` | 302 redirect to the long URL |

```
bash run.sh                      # http://localhost:8080   (PORT and DATA_FILE env vars are honoured)
curl -X POST localhost:8080/api/links -d '{"url":"https://openjdk.org"}'
curl -i localhost:8080/<code>
bash run.sh --demo               # start, act as a client (50 parallel clicks), stop
```

**Uses (Chapter 9):** `HttpServer`, `HttpClient` with async requests, virtual threads, `ConcurrentHashMap`, `AtomicLong`, `synchronized`, regex validation, `java.time`, environment variables, JSON, files.
