package org.biblioteca.domain.usuario;

import org.biblioteca.config.model.Identity;

public abstract class Usuario implements Identity<String> {
    private final String nome;
    private String codigo;

    public Usuario(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getId() {
        return codigo;
    }

    public void setId(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public abstract int getLimiteEmprestimos();

    public abstract int getTempoEmprestimo();

    public abstract boolean podePegarEmprestado();

}
