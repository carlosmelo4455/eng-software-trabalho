package org.biblioteca.domain.notificacao;

import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

import java.util.List;
import java.util.Optional;

@Singleton
public class NotificacaoServiceImpl implements NotificacaoService {

    private final NotificacaoRepository notificacaoRepository = NotificacaoRepositoryImpl.getInstance();

    private NotificacaoServiceImpl() {
    }

    public static NotificacaoServiceImpl getInstance() {
        return SingletonManager.getInstance(NotificacaoServiceImpl.class);
    }

    @Override
    public Notificacao adicionar(Notificacao notificacao) {
        return notificacaoRepository.save(notificacao);
    }

    @Override
    public Optional<Notificacao> buscarPorId(Long id) {
        return notificacaoRepository.findById(id);
    }

    @Override
    public List<Notificacao> listarTodos() {
        return notificacaoRepository.findAll();
    }

    @Override
    public Notificacao atualizar(Notificacao notificacao) {
        return notificacaoRepository.update(notificacao);
    }

    @Override
    public void remover(Notificacao notificacao) {
        notificacaoRepository.delete(notificacao);
    }
}