package org.biblioteca.domain.exemplar;

import org.biblioteca.observer.Observer;
import org.biblioteca.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class Livro implements Subject {
    private String codigo;
    private String titulo;
    private String editora;
    private String[] autores;
    private String edicao;
    private int anoPublicacao;
    private final List<Observer> observers = new ArrayList<>();
    private int reservas = 0;

    public Livro(String codigo, String titulo, String editora, String[] autores, String edicao, int anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String[] getAutores() {
        return autores;
    }

    public void setAutores(String[] autores) {
        this.autores = autores;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }


    @Override
    public void registrarObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notificarObserver() {
        observers.forEach(Observer::update);
    }

    public void registrarReserva() {
        reservas++;
        if (reservas == 2) {
            notificarObserver();
        }
    }

    public int getReservas() {
        return reservas;
    }
}
