package org.biblioteca.domain.transacao.reserva;

import org.biblioteca.config.service.AbstractService;
import org.biblioteca.domain.usuario.Professor;
import org.biblioteca.domain.usuario.Usuario;
import org.biblioteca.exception.LimiteReservasExcedidoException;

import java.util.List;
import java.util.Optional;

public class ReservaServiceImpl extends AbstractService<Reserva, Long> implements ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        super(reservaRepository);
        this.reservaRepository = reservaRepository;
    }

    @Override
    public Optional<Reserva> buscarReservaPorCodigoUsuarioECodigoLivro(String codigoUsuario, String codigoLivro) {
        return Optional.ofNullable(reservaRepository.buscarReservaPorCodigoUsuarioECodigoLivro(codigoUsuario, codigoLivro));
    }

    @Override
    public List<Reserva> buscarReservasPorCodigoLivro(String codigoLivro) {
        return reservaRepository.buscarReservasPorCodigoLivro(codigoLivro);
    }

    @Override
    public void verificarLimiteReservas(String codigoUsuario, int limiteReservas) {
        if (reservaRepository.contarReservasPorUsuario(codigoUsuario) >= limiteReservas) {
            throw new LimiteReservasExcedidoException("Usuário já possui " + limiteReservas + " reservas ativas.");
        }
    }

    @Override
    public void verificarSeUsuarioTemReservaAtiva(String codigoUsuario, String codigoLivro) {
        if (reservaRepository.buscarReservaPorCodigoUsuarioECodigoLivro(codigoUsuario, codigoLivro) != null) {
            throw new LimiteReservasExcedidoException("Usuário já possui uma reserva ativa do livro.");
        }
    }
}