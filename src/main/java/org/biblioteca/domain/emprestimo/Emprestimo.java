package org.biblioteca.domain.emprestimo;

import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.usuario.Usuario;

import java.time.LocalDate;

public class Emprestimo {
    private final Usuario usuario;
    private final Exemplar exemplar;
    private final LocalDate dataEmprestimo;
    private final LocalDate dataDevolucao;

    public Emprestimo(Usuario usuario, Exemplar exemplar) {
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plusDays(usuario.getTempoEmprestimo());
        this.exemplar.setDisponivel(false);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
}
