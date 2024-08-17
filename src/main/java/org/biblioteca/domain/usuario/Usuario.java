package org.biblioteca.domain.usuario;

import org.biblioteca.config.model.Identity;

public abstract class Usuario implements Identity<String> {
    private String codigo;
    private String nome;

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract int getLimiteEmprestimos();

    public abstract int getTempoEmprestimo();

    public abstract boolean podeEmprestar();

}
