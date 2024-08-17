package org.biblioteca.config.repository;

import org.biblioteca.config.model.Identity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CacheRepositoryImpl<T extends Identity<ID>, ID> implements CacheRepository<T, ID> {

    protected final Map<ID, T> store = new ConcurrentHashMap<>();
    protected final AtomicLong idGenerator = new AtomicLong();

    protected CacheRepositoryImpl() {
    }

    @SuppressWarnings("unchecked")
    public T save(T entity) {
        ID id = entity.getId();
        if (id == null) {
            id = (ID) Long.valueOf(idGenerator.incrementAndGet());
            entity.setId(id);
        }
        store.put(id, entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        ID id = entity.getId();
        if (id == null) {
            throw new IllegalArgumentException("Entidade sem identificador");
        }
        store.put(id, entity);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(ID id) {
        store.remove(id);
    }

    @Override
    public void delete(T entity) {
        store.values().remove(entity);
    }
}