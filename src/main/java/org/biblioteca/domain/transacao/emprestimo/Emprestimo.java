package org.biblioteca.domain.transacao.emprestimo;

import org.biblioteca.config.model.Identity;
import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.transacao.Transacao;
import org.biblioteca.domain.usuario.Usuario;

import java.time.LocalDate;

public class Emprestimo implements Identity<Long>, Transacao {
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

    @Override
    public Livro getLivro() {
        return exemplar.getLivro();
    }

    @Override
    public LocalDate getDataTransacao() {
        return dataEmprestimo;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
}
