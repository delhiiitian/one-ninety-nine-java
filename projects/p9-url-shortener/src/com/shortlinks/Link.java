package com.shortlinks;

import java.time.Instant;

/** One short link. Clicks live in the store (they change); everything here is fixed. */
public record Link(String code, String targetUrl, Instant createdAt) {

    public String toCsv() {
        return code + "," + createdAt + "," + targetUrl;     // URL last: it may contain commas
    }

    public static Link fromCsv(String line) {
        String[] p = line.split(",", 3);
        return new Link(p[0], p[2], Instant.parse(p[1]));
    }
}
