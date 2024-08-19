package org.biblioteca.domain.notificacao;

import java.util.List;
import java.util.Optional;

public interface NotificacaoService {
    Notificacao adicionar(Notificacao notificacao);

    Optional<Notificacao> buscarPorId(Long id);

    List<Notificacao> listarTodos();

    Notificacao atualizar(Notificacao notificacao);

    void remover(Notificacao notificacao);
}
