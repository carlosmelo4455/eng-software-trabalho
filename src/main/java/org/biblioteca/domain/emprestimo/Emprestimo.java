package org.biblioteca.domain.emprestimo;

import org.biblioteca.config.model.Identity;
import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.usuario.Usuario;

import java.time.LocalDate;

public class Emprestimo implements Identity<Long> {
    private final Usuario usuario;
    private final Exemplar exemplar;
    private final LocalDate dataEmprestimo;
    private final LocalDate dataDevolucao;
    private Long id;

    public Emprestimo(Usuario usuario, Exemplar exemplar) {
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plusDays(usuario.getTempoEmprestimo());
        this.exemplar.setDisponivel(false);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
