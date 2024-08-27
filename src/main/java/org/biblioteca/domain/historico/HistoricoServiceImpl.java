package org.biblioteca.domain.historico;

import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;
import org.biblioteca.domain.transacao.Transacao;
import org.biblioteca.domain.usuario.Professor;
import org.biblioteca.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;

@Singleton
public class HistoricoServiceImpl implements HistoricoService {

    private final HistoricoRepository historicoRepository = HistoricoRepositoryImpl.getInstance();

    private HistoricoServiceImpl() {
    }

    public static HistoricoServiceImpl getInstance() {
        return SingletonManager.getInstance(HistoricoServiceImpl.class);
    }


    @Override
    public Historico adicionar(Historico reserva) {
        return historicoRepository.save(reserva);
    }

    @Override
    public Optional<Historico> buscarPorId(Long id) {
        return historicoRepository.findById(id);
    }

    @Override
    public List<Historico> listarTodos() {
        return historicoRepository.findAll();
    }

    @Override
    public Historico atualizar(Historico reserva) {
        return historicoRepository.update(reserva);
    }

    @Override
    public void remover(Historico reserva) {
        historicoRepository.delete(reserva);
    }

    @Override
    public void salvarNoHistorico(Transacao transacao, Acao acao) {
        Historico historico = new Historico(transacao, acao);
        historicoRepository.save(historico);
    }
}