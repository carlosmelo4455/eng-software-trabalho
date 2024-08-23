package org.biblioteca.domain.historico;

import org.biblioteca.config.repository.CacheRepositoryImpl;
import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

import java.util.List;

@Singleton
public class HistoricoRepositoryImpl extends CacheRepositoryImpl<Historico, Long> implements HistoricoRepository {

    private HistoricoRepositoryImpl() {
    }

    public static HistoricoRepositoryImpl getInstance() {
        return SingletonManager.getInstance(HistoricoRepositoryImpl.class);
    }
}