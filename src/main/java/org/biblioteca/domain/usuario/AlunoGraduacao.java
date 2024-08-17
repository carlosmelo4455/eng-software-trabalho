package org.biblioteca.domain.usuario;

public class AlunoGraduacao extends Usuario {

    public AlunoGraduacao(String codigo, String nome) {
        super(codigo, nome);
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
    public boolean podeEmprestar() {
        // Implementar lógica pra isso
        return true;
    }
}