package org.biblioteca.domain.reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaService {
    Reserva adicionar(Reserva reserva);

    Optional<Reserva> buscarPorId(Long id);

    List<Reserva> listarTodos();

    Reserva atualizar(Reserva reserva);

    void remover(Reserva reserva);
}
