package org.biblioteca.domain.reserva;

import org.biblioteca.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface ReservaService {
    Reserva adicionar(Reserva reserva);

    Optional<Reserva> buscarPorId(Long id);

    List<Reserva> listarTodos();

    Reserva atualizar(Reserva reserva);

    void remover(Reserva reserva);

    boolean podeReservar(Usuario usuario, String codigoLivro);

    Long contarReservasPorUsuario(String codigoUsuario);

    Optional<Reserva> buscarReservaPorCodigoUsuarioECodigoLivro(String codigoUsuario, String codigoLivro);

    List<Reserva> buscarReservasPorCodigoLivro(String codigoLivro);
}
