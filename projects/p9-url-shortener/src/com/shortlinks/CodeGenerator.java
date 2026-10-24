package com.shortlinks;

import java.security.SecureRandom;

/** Makes short codes like "aZ3k9Q" from 62 characters: 62^6 = 56 billion combinations. */
public class CodeGenerator {
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final SecureRandom random;
    private final int length;

    public CodeGenerator(int length, SecureRandom random) {
        this.length = length;
        this.random = random;
    }

    public String next() {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
}
