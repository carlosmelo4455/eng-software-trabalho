package org.biblioteca.domain.emprestimo;

import java.util.List;
import java.util.Optional;

public interface EmprestimoService {
    Emprestimo adicionar(Emprestimo emprestimo);

    Optional<Emprestimo> buscarPorId(Long id);

    List<Emprestimo> listarTodos();

    Emprestimo atualizar(Emprestimo emprestimo);

    void remover(Emprestimo emprestimo);
}
