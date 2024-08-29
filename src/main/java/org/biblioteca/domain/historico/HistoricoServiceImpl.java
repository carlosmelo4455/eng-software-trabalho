package org.biblioteca.domain.historico;

import org.biblioteca.config.service.AbstractService;
import org.biblioteca.domain.transacao.Transacao;

import java.util.Arrays;
import java.util.List;

public class HistoricoServiceImpl extends AbstractService<Historico, Long> implements HistoricoService {

    private final HistoricoRepository historicoRepository;

    public HistoricoServiceImpl(HistoricoRepository historicoRepository) {
        super(historicoRepository);
        this.historicoRepository = historicoRepository;
    }

    @Override
    public void salvarNoHistorico(Transacao transacao, Acao acao) {
        Historico historico = new Historico(transacao, acao);
        historicoRepository.save(historico);
    }

    @Override
    public List<Historico> buscarHistoricosEmprestimoPorUsuario(String codigoUsuario) {
        List<Historico> historicos;
        historicos = historicoRepository.buscarHistoricosPorUsuarioEAcoes(codigoUsuario, Arrays.asList(Acao.SOLICITACAO_EMPRESTIMO, Acao.DEVOLUCAO_EMPRESTIMO));
        return historicos;
    }

    @Override
    public List<Historico> buscarHistoricosReservaPorUsuario(String codigoUsuario) {
        List<Historico> historicos;
        historicos = historicoRepository.buscarHistoricosPorUsuarioEAcoes(codigoUsuario, Arrays.asList(Acao.SOLICITACAO_RESERVA, Acao.CANCELAMENTO_RESERVA));
        return historicos;
    }
}