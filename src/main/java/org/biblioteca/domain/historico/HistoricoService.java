package org.biblioteca.domain.historico;

import org.biblioteca.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface HistoricoService {
    Historico adicionar(Historico reserva);

    Optional<Historico> buscarPorId(Long id);

    List<Historico> listarTodos();

    Historico atualizar(Historico reserva);

    void remover(Historico reserva);
}
