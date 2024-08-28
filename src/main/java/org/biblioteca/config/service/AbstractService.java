package org.biblioteca.config.service;

import org.biblioteca.config.repository.CacheRepository;

public abstract class AbstractService<T, K> implements BaseService<T, K> {

    protected final CacheRepository<T, K> repo;

    public AbstractService(CacheRepository<T, K> cacheRepository) {
        this.repo = cacheRepository;
    }

    @Override
    public T adicionar(T entity) {
        return repo.save(entity);
    }

    @Override
    public T buscarPorId(K id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Iterable<T> listarTodos() {
        return repo.findAll();
    }

    @Override
    public T atualizar(T entity) {
        return repo.update(entity);
    }

    @Override
    public void remover(T entity) {
        repo.delete(entity);
    }
}