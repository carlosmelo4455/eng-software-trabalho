package org.biblioteca.model;

public abstract class Usuario {
    private final String codigo;
    private final String nome;

    public Usuario(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public abstract int getLimiteEmprestimos();
    public abstract int getTempoEmprestimo();
    public abstract boolean podeEmprestar();

}
