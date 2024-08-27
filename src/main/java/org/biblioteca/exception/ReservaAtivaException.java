package org.biblioteca.exception;

public class ReservaAtivaException extends RuntimeException {
    public ReservaAtivaException() {
        super("Usuário já possui uma reserva ativa para este livro");
    }
}
