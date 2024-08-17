package org.biblioteca.domain.livro;

import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

import java.util.List;
import java.util.Optional;

@Singleton
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository = LivroRepositoryImpl.getInstance();

    private LivroServiceImpl() {
    }

    public static LivroServiceImpl getInstance() {
        return SingletonManager.getInstance(LivroServiceImpl.class);
    }

    @Override
    public Livro adicionarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    @Override
    public Optional<Livro> buscarLivroPorCodigo(String codigo) {
        return livroRepository.findById(codigo);
    }

    @Override
    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    @Override
    public Livro atualizarLivro(Livro livroAtualizado) {
        return livroRepository.update(livroAtualizado);
    }

    @Override
    public void removerLivro(Livro livro) {
        livroRepository.delete(livro);
    }
}