package org.biblioteca.strategy;

import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.exemplar.ExemplarService;
import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.historico.Acao;
import org.biblioteca.domain.historico.HistoricoService;
import org.biblioteca.domain.transacao.emprestimo.Emprestimo;
import org.biblioteca.domain.transacao.emprestimo.EmprestimoService;
import org.biblioteca.domain.transacao.reserva.Reserva;
import org.biblioteca.domain.transacao.reserva.ReservaService;
import org.biblioteca.domain.usuario.Usuario;

import java.util.List;

public class EmprestimoComReservaStrategy implements EmprestimoStrategy {
    private final ReservaService reservaService;
    private final ExemplarService exemplarService;
    private final EmprestimoService emprestimoService;
    private final HistoricoService historicoService;

    public EmprestimoComReservaStrategy(ReservaService reservaService, ExemplarService exemplarService, EmprestimoService emprestimoService, HistoricoService historicoService) {
        this.reservaService = reservaService;
        this.exemplarService = exemplarService;
        this.emprestimoService = emprestimoService;
        this.historicoService = historicoService;
    }

    @Override
    public Emprestimo emprestar(Usuario usuario, Livro livro, List<Exemplar> exemplaresDisponiveis, Reserva reserva){
        reservaService.remover(reserva);
        Exemplar exemplar = exemplaresDisponiveis.get(0);
        exemplar.setDisponivel(false);
        exemplarService.atualizar(exemplar);
        historicoService.salvarNoHistorico(reserva, Acao.CANCELAMENTO_RESERVA);
        return new Emprestimo(usuario, exemplar);
    }
}