package org.biblioteca.domain.transacao.emprestimo;

import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface EmprestimoService {
    Emprestimo adicionar(Emprestimo emprestimo);

    Optional<Emprestimo> buscarPorId(Long id);

    List<Emprestimo> listarTodos();

    Emprestimo atualizar(Emprestimo emprestimo);

    void remover(Emprestimo emprestimo);

    Optional<Emprestimo> buscarEmprestimoPorCodigoUsuarioECodigoLivro(String codigoUsuario, String codigoLivro);

    List<Emprestimo> buscarEmprestimosPorCodigoUsuario(String codigoUsuario);
}
