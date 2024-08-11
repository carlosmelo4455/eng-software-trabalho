package org.biblioteca.services.impl;

import org.biblioteca.model.Livro;
import org.biblioteca.services.interfaces.LivroService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivroServiceImpl implements LivroService {

    private static LivroServiceImpl instance;
    private final List<Livro> livros;

    private LivroServiceImpl() {
        this.livros = new ArrayList<>();
    }

    public static synchronized LivroServiceImpl getInstance() {
        if (instance == null) {
            instance = new LivroServiceImpl();
        }
        return instance;
    }


    @Override
    public Livro adicionarLivro(Livro livro) {
        livros.add(livro);
        return livro;
    }

    @Override
    public Livro buscarLivroPorCodigo(String codigo) {
        Optional<Livro> livro = livros.stream()
                .filter(l -> l.getCodigo().equals(codigo))
                .findFirst();
        return livro.orElse(null);
    }

    @Override
    public List<Livro> listarTodosLivros() {
        return new ArrayList<>(livros);
    }

    @Override
    public Livro atualizarLivro(String codigo, Livro livroAtualizado) {
        Livro livroExistente = buscarLivroPorCodigo(codigo);
        if (livroExistente != null) {
            livroExistente.setTitulo(livroAtualizado.getTitulo());
            livroExistente.setAutores(livroAtualizado.getAutores());
            livroExistente.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
            return livroExistente;
        }
        return null;
    }

    @Override
    public boolean removerLivro(String codigo) {
        return livros.removeIf(l -> l.getCodigo().equals(codigo));
    }
}