package com.shortlinks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Just enough JSON for this API (Chapter 9.8). A real service would use Jackson. */
public final class Json {
    private Json() { }

    public static String quote(String s) {
        return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }

    /** Reads one string field such as {"url": "https://..."}; returns null if missing. */
    public static String stringField(String json, String name) {
        Matcher m = Pattern.compile("\"" + Pattern.quote(name) + "\"\\s*:\\s*\"((?:[^\"\\\\]|\\\\.)*)\"").matcher(json);
        return m.find() ? m.group(1).replace("\\\"", "\"").replace("\\\\", "\\") : null;
    }

    public static String error(String message) {
        return "{\"error\":" + quote(message) + "}";
    }
}
