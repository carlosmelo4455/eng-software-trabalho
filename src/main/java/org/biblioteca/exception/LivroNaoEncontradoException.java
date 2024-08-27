package org.biblioteca.exception;

public class LivroNaoEncontradoException extends RuntimeException {
    public LivroNaoEncontradoException() {
        super("Livro não encontrado");
    }
}
