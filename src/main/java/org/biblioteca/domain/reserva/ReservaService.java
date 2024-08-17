package org.biblioteca.domain.reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaService {
    Reserva adicionarReserva(Reserva reserva);

    Optional<Reserva> buscarReservaPorId(Long id);

    List<Reserva> listarTodasReservas();

    Reserva atualizarReserva(Reserva reserva);

    void removerReserva(Reserva reserva);
}
