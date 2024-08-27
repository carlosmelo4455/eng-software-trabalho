package org.biblioteca.strategy;

import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.transacao.emprestimo.Emprestimo;
import org.biblioteca.domain.transacao.reserva.Reserva;
import org.biblioteca.domain.usuario.Usuario;

import java.util.List;

public interface EmprestimoStrategy {
    Emprestimo emprestar(Usuario usuario, Livro livro, List<Exemplar> exemplaresDisponiveis, Reserva reserva) throws Exception;
}