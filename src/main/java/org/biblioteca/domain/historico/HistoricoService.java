package org.biblioteca.domain.historico;

import org.biblioteca.config.service.BaseService;
import org.biblioteca.domain.transacao.Transacao;
import org.biblioteca.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface HistoricoService extends BaseService<Historico, Long> {

    void salvarNoHistorico(Transacao transacao, Acao acao);
}
