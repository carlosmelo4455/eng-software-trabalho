package org.biblioteca.exception;

public class LimiteEmprestimoExcedidoException extends RuntimeException {
    public LimiteEmprestimoExcedidoException() {
        super("Limite de empréstimos excedido");
    }
}
