package org.biblioteca.strategy;

import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.exemplar.ExemplarService;
import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.transacao.emprestimo.Emprestimo;
import org.biblioteca.domain.transacao.reserva.Reserva;
import org.biblioteca.domain.transacao.reserva.ReservaService;
import org.biblioteca.domain.usuario.Usuario;

import java.util.List;

public class EmprestimoSemReservaStrategy implements EmprestimoStrategy {
    private final ExemplarService exemplarService;
    private final ReservaService reservaService;

    public EmprestimoSemReservaStrategy(ExemplarService exemplarService, ReservaService reservaService) {
        this.exemplarService = exemplarService;
        this.reservaService = reservaService;
    }

    @Override
    public Emprestimo emprestar(Usuario usuario, Livro livro, List<Exemplar> exemplaresDisponiveis, Reserva reserva) throws Exception {
        List<Reserva> reservas = reservaService.buscarReservasPorCodigoLivro(livro.getCodigo());
        if (reservas.size() < exemplaresDisponiveis.size() || usuario.podePegarEmprestado()) {
            Exemplar exemplar = exemplaresDisponiveis.get(0);
            exemplar.setDisponivel(false);
            exemplarService.atualizar(exemplar);
            return new Emprestimo(usuario, exemplar);
        } else {
            throw new Exception("Não há exemplares disponíveis do livro.");
        }
    }
}