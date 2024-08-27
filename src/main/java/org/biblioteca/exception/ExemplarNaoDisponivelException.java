package org.biblioteca.exception;

public class ExemplarNaoDisponivelException extends RuntimeException {
    public ExemplarNaoDisponivelException() {
        super("Exemplar não disponível");
    }
}
