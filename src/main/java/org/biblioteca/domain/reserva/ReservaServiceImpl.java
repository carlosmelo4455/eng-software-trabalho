package org.biblioteca.domain.reserva;

import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

import java.util.List;
import java.util.Optional;

@Singleton
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository = ReservaRepositoryImpl.getInstance();

    private ReservaServiceImpl() {
    }

    public static ReservaServiceImpl getInstance() {
        return SingletonManager.getInstance(ReservaServiceImpl.class);
    }


    @Override
    public Reserva adicionarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Optional<Reserva> buscarReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public List<Reserva> listarTodasReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva atualizarReserva(Reserva reserva) {
        return reservaRepository.update(reserva);
    }

    @Override
    public void removerReserva(Reserva reserva) {
        reservaRepository.delete(reserva);
    }
}