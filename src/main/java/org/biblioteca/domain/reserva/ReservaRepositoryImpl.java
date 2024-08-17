package org.biblioteca.domain.reserva;

import org.biblioteca.config.repository.CacheRepositoryImpl;
import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

@Singleton
public class ReservaRepositoryImpl extends CacheRepositoryImpl<Reserva, Long> implements ReservaRepository {

    private ReservaRepositoryImpl() {
    }

    public static ReservaRepositoryImpl getInstance() {
        return SingletonManager.getInstance(ReservaRepositoryImpl.class);
    }
}