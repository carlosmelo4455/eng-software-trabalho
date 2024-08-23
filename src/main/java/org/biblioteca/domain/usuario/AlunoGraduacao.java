package org.biblioteca.domain.usuario;

public class AlunoGraduacao extends Usuario {

    public AlunoGraduacao(String codigo, String nome) {
        super(codigo, nome);
    }

    @Override
    public String getNome() {
        return "";
    }

    @Override
    public void setNome(String nome) {

    }

    @Override
    public int getLimiteEmprestimos() {
        return 3;
    }

    @Override
    public int getTempoEmprestimo() {
        return 3;
    }

    @Override
    public boolean podePegarEmprestado() {
        return false;
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public void setId(String s) {

    }
}