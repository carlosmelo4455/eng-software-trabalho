package org.biblioteca.domain.historico;

import org.biblioteca.config.service.AbstractService;
import org.biblioteca.domain.transacao.Transacao;

import java.util.List;
import java.util.Optional;

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
}