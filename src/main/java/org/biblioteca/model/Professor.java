package org.biblioteca.model;

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
    public boolean podeEmprestar() {
        // Implementar lógica
        return true;
    }
}