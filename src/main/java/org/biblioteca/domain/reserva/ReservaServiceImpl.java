package org.biblioteca.domain.reserva;

import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;
import org.biblioteca.domain.usuario.Professor;
import org.biblioteca.domain.usuario.Usuario;

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
    public Reserva adicionar(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public List<Reserva> listarTodos() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva atualizar(Reserva reserva) {
        return reservaRepository.update(reserva);
    }

    @Override
    public void remover(Reserva reserva) {
        reservaRepository.delete(reserva);
    }

    @Override
    public boolean podeReservar(Usuario usuario, String codigoLivro) {
        if (usuario.getClass().isInstance(Professor.class)) {
            return true;
        } else {
            return reservaRepository.findAll().stream()
                    .filter(reserva -> reserva.getUsuario().equals(usuario))
                    .noneMatch(reserva -> reserva.getLivro().getCodigo().equals(codigoLivro));
        }
    }

    @Override
    public Long contarReservasPorUsuario(String codigoUsuario) {
        return reservaRepository.contarReservasPorUsuario(codigoUsuario);
    }

    @Override
    public Optional<Reserva> buscarReservaPorCodigoUsuarioECodigoLivro(String codigoUsuario, String codigoLivro) {
        return Optional.ofNullable(reservaRepository.buscarReservaPorCodigoUsuarioECodigoLivro(codigoUsuario, codigoLivro));
    }

    @Override
    public List<Reserva> buscarReservasPorCodigoLivro(String codigoLivro) {
        return reservaRepository.buscarReservasPorCodigoLivro(codigoLivro);
    }
}