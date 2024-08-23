package org.biblioteca.domain.reserva;

import org.biblioteca.config.repository.CacheRepository;

import java.util.List;

public interface ReservaRepository extends CacheRepository<Reserva, Long> {
    Long contarReservasPorUsuario(String codigoUsuario);

    Reserva buscarReservaPorCodigoUsuarioECodigoLivro(String codigoUsuario, String codigoLivro);

    List<Reserva> buscarReservasPorCodigoLivro(String codigoLivro);
}
