package org.biblioteca.domain.livro;

import org.biblioteca.config.repository.CacheRepositoryImpl;
import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

@Singleton
public class LivroRepositoryImpl extends CacheRepositoryImpl<Livro, String> implements LivroRepository {

    private LivroRepositoryImpl() {
    }

    public static LivroRepositoryImpl getInstance() {
        return SingletonManager.getInstance(LivroRepositoryImpl.class);
    }
}