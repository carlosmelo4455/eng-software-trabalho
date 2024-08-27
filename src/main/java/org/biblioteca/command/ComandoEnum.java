// ComandoEnum.java
package org.biblioteca.command;

public enum ComandoEnum {
    EMPRESTIMO("emp", EmprestimoCommand.class),
    RESERVA("res", ReservaCommand.class),
    DEVOLUCAO("dev", DevolucaoCommand.class),
    OBSERVACAO("obs", ObservacaoCommand.class),
    INFORMACOES_LIVRO("liv", InformacoesLivroCommand.class),
    INFORMACOES_USUARIO("usu", InformacoesUsuarioCommand.class),
    NOTIFICACOES("ntf", NotificacoesCommand.class),
    SAIR("sai", SairCommand.class);

    private final String comando;
    private final Class<? extends Command> commandClass;

    ComandoEnum(String comando, Class<? extends Command> commandClass) {
        this.comando = comando;
        this.commandClass = commandClass;
    }

    public String getComando() {
        return comando;
    }

    public Class<? extends Command> getCommandClass() {
        return commandClass;
    }

    public static ComandoEnum fromString(String comando) {
        for (ComandoEnum c : ComandoEnum.values()) {
            if (c.getComando().equalsIgnoreCase(comando)) {
                return c;
            }
        }
        return null;
    }
}