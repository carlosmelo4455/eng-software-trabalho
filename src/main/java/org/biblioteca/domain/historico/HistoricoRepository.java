package org.biblioteca.domain.historico;

import org.biblioteca.config.repository.CacheRepository;

import java.util.List;

public interface HistoricoRepository extends CacheRepository<Historico, Long> {
    List<Historico> buscarHistoricosPorUsuarioEAcoes(String codigoUsuario, List<Acao> list);
}
