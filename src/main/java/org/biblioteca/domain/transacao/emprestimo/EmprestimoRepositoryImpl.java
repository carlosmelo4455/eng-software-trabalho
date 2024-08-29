package org.biblioteca.domain.transacao.emprestimo;

import org.biblioteca.config.repository.CacheRepositoryImpl;
import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

import java.util.List;
import java.util.Optional;

@Singleton
public class EmprestimoRepositoryImpl extends CacheRepositoryImpl<Emprestimo, Long> implements EmprestimoRepository {

    private EmprestimoRepositoryImpl() {
    }

    public static EmprestimoRepositoryImpl getInstance() {
        return SingletonManager.getInstance(EmprestimoRepositoryImpl.class);
    }

    @Override
    public Optional<Emprestimo> findEmprestimoPorCodigoUsuarioAndCodigoLivro(String codigoUsuario, String codigoLivro) {
        return this.findAll().stream()
                .filter(emprestimo -> emprestimo.getUsuario().getId().equals(codigoUsuario) && emprestimo.getExemplar().getLivro().getCodigo().equals(codigoLivro))
                .findFirst();
    }

    @Override
    public List<Emprestimo> buscarEmprestimosPorCodigoUsuario(String codigoUsuario) {
        return this.findAll().stream()
                .filter(emprestimo -> emprestimo.getUsuario().getId().equals(codigoUsuario))
                .toList();
    }

    @Override
    public Optional<Emprestimo> findEmprestimoPorCodigoExemplar(String codigoExemplar) {
        return this.findAll().stream()
                .filter(emprestimo -> emprestimo.getExemplar().getId().equals(codigoExemplar))
                .findFirst();
    }
}