package org.biblioteca.domain.exemplar;

import org.biblioteca.config.model.Identity;
import org.biblioteca.domain.livro.Livro;

public class Exemplar implements Identity<String> {

    private final Livro livro;
    private String codigoExemplar;
    private boolean disponivel;

    public Exemplar(Livro livro, String codigoExemplar) {
        this.livro = livro;
        this.codigoExemplar = codigoExemplar;
        this.disponivel = true;
    }

    @Override
    public String getId() {
        return codigoExemplar;
    }

    @Override
    public void setId(String codigoExemplar) {
        this.codigoExemplar = codigoExemplar;
    }

    public Livro getLivro() {
        return livro;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }


}
