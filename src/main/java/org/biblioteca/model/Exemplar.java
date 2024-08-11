package org.biblioteca.model;

public class Exemplar {
    private final Livro livro;
    private final String codigoExemplar;
    private boolean disponivel;

    public Exemplar(Livro livro, String codigoExemplar) {
        this.livro = livro;
        this.codigoExemplar = codigoExemplar;
        this.disponivel = true;
    }

    public Livro getLivro() {
        return livro;
    }

    public String getCodigoExemplar() {
        return codigoExemplar;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
