package org.biblioteca.domain.notificacao;

import org.biblioteca.config.repository.CacheRepositoryImpl;
import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

@Singleton
public class NotificacaoRepositoryImpl extends CacheRepositoryImpl<Notificacao, Long> implements NotificacaoRepository {

    private NotificacaoRepositoryImpl() {
    }

    public static NotificacaoRepositoryImpl getInstance() {
        return SingletonManager.getInstance(NotificacaoRepositoryImpl.class);
    }
}