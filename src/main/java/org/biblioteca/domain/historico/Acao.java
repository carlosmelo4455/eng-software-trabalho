package org.biblioteca.domain.historico;

public enum Acao {
    SOLICITACAO_EMPRESTIMO("Solicitacao de Emprestimo"),
    CANCELAMENTO_EMPRESTIMO("Cancelamento de Emprestimo"),
    SOLICITACAO_RESERVA("Solicitacao de Reserva"),
    CANCELAMENTO_RESERVA("Cancelamento de Reserva"),
    DEVOLUCAO_EMPRESTIMO("Devolucao de Emprestimo");

    private final String descricao;

    Acao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
