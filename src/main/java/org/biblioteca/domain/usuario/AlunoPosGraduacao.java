package org.biblioteca.domain.usuario;

public class AlunoPosGraduacao extends Usuario {

    public AlunoPosGraduacao(String codigo, String nome) {
        super(codigo, nome);
    }

    @Override
    public int getLimiteEmprestimos() {
        return 4;
    }

    @Override
    public int getTempoEmprestimo() {
        return 5;
    }

    @Override
    public boolean podeEmprestar() {
        // Implementar lógica
        return true;
    }
}