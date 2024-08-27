package org.biblioteca.domain.transacao.reserva;

import org.biblioteca.config.repository.CacheRepositoryImpl;
import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

import java.util.List;

@Singleton
public class ReservaRepositoryImpl extends CacheRepositoryImpl<Reserva, Long> implements ReservaRepository {

    private ReservaRepositoryImpl() {
    }

    public static ReservaRepositoryImpl getInstance() {
        return SingletonManager.getInstance(ReservaRepositoryImpl.class);
    }

    @Override
    public Long contarReservasPorUsuario(String codigoUsuario) {
        return this.store.values().stream()
                .filter(reserva -> reserva.getUsuario().getId().equals(codigoUsuario))
                .count();
    }

    @Override
    public Reserva buscarReservaPorCodigoUsuarioECodigoLivro(String codigoUsuario, String codigoLivro) {
        return this.store.values().stream()
                .filter(reserva -> reserva.getUsuario().getId().equals(codigoUsuario))
                .filter(reserva -> reserva.getLivro().getCodigo().equals(codigoLivro))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Reserva> buscarReservasPorCodigoLivro(String codigoLivro) {
        return this.store.values().stream()
                .filter(reserva -> reserva.getLivro().getCodigo().equals(codigoLivro))
                .toList();
    }
}