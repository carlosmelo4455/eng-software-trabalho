package org.biblioteca.domain.exemplar;

import org.biblioteca.config.repository.CacheRepositoryImpl;
import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

@Singleton
public class ExemplarRepositoryImpl extends CacheRepositoryImpl<Exemplar, String> implements ExemplarRepository {

    private ExemplarRepositoryImpl() {
    }

    public static ExemplarRepositoryImpl getInstance() {
        return SingletonManager.getInstance(ExemplarRepositoryImpl.class);
    }
}