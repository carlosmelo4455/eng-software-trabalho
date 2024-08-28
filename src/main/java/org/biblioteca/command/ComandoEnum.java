// ComandoEnum.java
package org.biblioteca.command;

public enum ComandoEnum {
    EMPRESTIMO("emp"),
    RESERVA("res"),
    DEVOLUCAO("dev"),
    OBSERVACAO("obs"),
    INFORMACOES_LIVRO("liv"),
    INFORMACOES_USUARIO("usu"),
    NOTIFICACOES("ntf"),
    SAIR("sai");

    private final String comando;

    ComandoEnum(String comando) {
        this.comando = comando;
    }

    public static ComandoEnum fromString(String comando) {
        for (ComandoEnum c : ComandoEnum.values()) {
            if (c.getComando().equalsIgnoreCase(comando)) {
                return c;
            }
        }
        return null;
    }

    public String getComando() {
        return comando;
    }
}