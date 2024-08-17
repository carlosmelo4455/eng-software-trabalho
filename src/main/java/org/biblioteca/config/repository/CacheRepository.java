package org.biblioteca.config.repository;

import java.util.List;
import java.util.Optional;

public interface CacheRepository<T, ID> {
    T save(T entity);

    T update(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    void delete(T entity);
}