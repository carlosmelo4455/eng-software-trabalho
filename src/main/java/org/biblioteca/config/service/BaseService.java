package org.biblioteca.config.service;

public interface BaseService<T, K> {

    T adicionar(T entity);

    T buscarPorId(K id);

    Iterable<T> listarTodos();

    T atualizar(T entity);

    void remover(T entity);
}
