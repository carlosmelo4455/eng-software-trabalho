package org.biblioteca.domain.historico;

import org.biblioteca.config.service.AbstractService;
import org.biblioteca.domain.transacao.Transacao;

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
    public List<Historico> buscarHistoricosPorUsuarioEAcao(String codigoUsuario, Acao acao) {
        return historicoRepository.buscarHistoricosPorUsuarioEAcao(codigoUsuario, acao);
    }
}