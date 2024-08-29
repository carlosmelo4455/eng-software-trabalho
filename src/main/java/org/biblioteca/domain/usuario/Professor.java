package org.biblioteca.domain.usuario;

public class Professor extends Usuario {

    public Professor(String codigo, String nome) {
        super(codigo, nome);
    }

    @Override
    public int getLimiteEmprestimos() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getTempoEmprestimo() {
        return 7;
    }

    @Override
    public boolean podePegarEmprestado() {
        return true;
    }

    @Override
    public boolean podeObservar() {
        return true;
    }
}