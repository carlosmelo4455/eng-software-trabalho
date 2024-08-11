package org.biblioteca.model;

public class Livro {
    private String codigo;
    private String titulo;
    private String editora;
    private String[] autores;
    private String edicao;
    private int anoPublicacao;

    public Livro(String codigo, String titulo, String editora, String[] autores, String edicao, int anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setAutores(String[] autores) {
        this.autores = autores;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditora() {
        return editora;
    }

    public String[] getAutores() {
        return autores;
    }

    public String getEdicao() {
        return edicao;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }
}
