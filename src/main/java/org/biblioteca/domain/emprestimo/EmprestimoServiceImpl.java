package org.biblioteca.domain.emprestimo;

import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

import java.util.List;
import java.util.Optional;

@Singleton
public class EmprestimoServiceImpl implements EmprestimoService {

    private final EmprestimoRepository emprestimoRepository = EmprestimoRepositoryImpl.getInstance();

    private EmprestimoServiceImpl() {
    }

    public static EmprestimoServiceImpl getInstance() {
        return SingletonManager.getInstance(EmprestimoServiceImpl.class);
    }

    @Override
    public Emprestimo adicionar(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    @Override
    public Optional<Emprestimo> buscarPorId(Long id) {
        return emprestimoRepository.findById(id);
    }

    @Override
    public List<Emprestimo> listarTodos() {
        return emprestimoRepository.findAll();
    }

    @Override
    public Emprestimo atualizar(Emprestimo emprestimo) {
        return emprestimoRepository.update(emprestimo);
    }

    @Override
    public void remover(Emprestimo emprestimo) {
        emprestimoRepository.delete(emprestimo);
    }

    @Override
    public Optional<Emprestimo> buscarEmprestimoPorCodigoUsuarioECodigoLivro(String codigoUsuario, String codigoLivro) {
        return emprestimoRepository.findEmprestimoPorCodigoUsuarioAndCodigoLivro(codigoUsuario, codigoLivro);
    }

    @Override
    public List<Emprestimo> buscarEmprestimosPorCodigoUsuario(String codigoUsuario) {
        return emprestimoRepository.buscarEmprestimosPorCodigoUsuario(codigoUsuario);
    }
}