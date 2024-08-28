package org.biblioteca.domain.transacao.reserva;

import org.biblioteca.config.service.BaseService;
import org.biblioteca.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface ReservaService extends BaseService<Reserva, Long> {

    Optional<Reserva> buscarReservaPorCodigoUsuarioECodigoLivro(String codigoUsuario, String codigoLivro);

    List<Reserva> buscarReservasPorCodigoLivro(String codigoLivro);

    void verificarLimiteReservas(String codigoUsuario, int limiteReservas);

    void verificarSeUsuarioTemReservaAtiva(String codigoUsuario, String codigoLivro);
}
