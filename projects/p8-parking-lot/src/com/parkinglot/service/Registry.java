package com.parkinglot.service;

import com.parkinglot.model.Identifiable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/** A tiny generic store for anything with an id (Chapter 8.7). Works for tickets, staff, passes... */
public class Registry<T extends Identifiable> {
    private final Map<String, T> items = new LinkedHashMap<>();

    public void add(T item) { items.put(item.id(), item); }
    public Optional<T> find(String id) { return Optional.ofNullable(items.get(id)); }
    public Optional<T> remove(String id) { return Optional.ofNullable(items.remove(id)); }
    public Collection<T> all() { return items.values(); }
    public int size() { return items.size(); }
}
