package org.biblioteca.domain.notificacao;

import org.biblioteca.config.service.AbstractService;

import java.util.List;
import java.util.Optional;

public class NotificacaoServiceImpl extends AbstractService<Notificacao, Long> implements NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoServiceImpl(NotificacaoRepository notificacaoRepository) {
        super(notificacaoRepository);
        this.notificacaoRepository = notificacaoRepository;
    }
}