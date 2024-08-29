package org.biblioteca.domain.transacao.emprestimo;

import org.biblioteca.config.repository.CacheRepository;

import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository extends CacheRepository<Emprestimo, Long> {
    Optional<Emprestimo> findEmprestimoPorCodigoUsuarioAndCodigoLivro(String codigoUsuario, String codigoLivro);

    List<Emprestimo> buscarEmprestimosPorCodigoUsuario(String codigoUsuario);

    Optional<Emprestimo> findEmprestimoPorCodigoExemplar(String codigoExemplar);
}
