package org.biblioteca.exception;

public class LimiteReservasExcedidoException extends RuntimeException {
    public LimiteReservasExcedidoException() {
        super("Limite de reservas excedido");
    }
}
