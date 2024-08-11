package org.biblioteca.services.impl;

import org.biblioteca.model.Reserva;
import org.biblioteca.model.Usuario;
import org.biblioteca.model.Livro;
import org.biblioteca.services.interfaces.ReservaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservaServiceImpl implements ReservaService {

    private final List<Reserva> reservas;
    private static ReservaServiceImpl instance;


    public ReservaServiceImpl() {
        this.reservas = new ArrayList<>();
    }

    public static synchronized ReservaServiceImpl getInstance() {
        if (instance == null) {
            instance = new ReservaServiceImpl();
        }
        return instance;
    }

    @Override
    public Reserva realizarReserva(Reserva reserva) {
        reservas.add(reserva);
        return reserva;
    }

    @Override
    public Reserva buscarReserva(Usuario usuario, Livro livro) {
        Optional<Reserva> reserva = reservas.stream()
                .filter(r -> r.getUsuario().equals(usuario) && r.getLivro().equals(livro))
                .findFirst();
        return reserva.orElse(null);
    }

    @Override
    public List<Reserva> listarReservas() {
        return new ArrayList<>(reservas);
    }

    @Override
    public boolean cancelarReserva(Usuario usuario, Livro livro) {
        return reservas.removeIf(r -> r.getUsuario().equals(usuario) && r.getLivro().equals(livro));
    }
}