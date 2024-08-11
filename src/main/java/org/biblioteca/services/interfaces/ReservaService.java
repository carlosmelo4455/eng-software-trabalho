package org.biblioteca.services.interfaces;

import org.biblioteca.model.Livro;
import org.biblioteca.model.Reserva;
import org.biblioteca.model.Usuario;

import java.util.List;

public interface ReservaService {
    Reserva realizarReserva(Reserva reserva);

    Reserva buscarReserva(Usuario usuario, Livro livro);

    List<Reserva> listarReservas();

    boolean cancelarReserva(Usuario usuario, Livro livro);
}