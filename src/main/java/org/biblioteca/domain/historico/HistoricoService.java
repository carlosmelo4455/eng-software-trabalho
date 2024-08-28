package org.biblioteca.domain.historico;

import org.biblioteca.config.service.BaseService;
import org.biblioteca.domain.transacao.Transacao;

import java.util.List;

public interface HistoricoService extends BaseService<Historico, Long> {

    void salvarNoHistorico(Transacao transacao, Acao acao);

    List<Historico> buscarHistoricosPorUsuarioEAcao(String codigoUsuario, Acao acao);
}
