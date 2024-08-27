package org.biblioteca.command;

import org.biblioteca.facade.BibliotecaFacade;

public class NotificacoesCommand implements Command {
    private final BibliotecaFacade bibliotecaFacade;
    private final String codigoUsuario;

    public NotificacoesCommand(BibliotecaFacade bibliotecaFacade, String codigoUsuario) {
        this.bibliotecaFacade = bibliotecaFacade;
        this.codigoUsuario = codigoUsuario;
    }

    @Override
    public void execute() {
        bibliotecaFacade.consultarNotificacoes(codigoUsuario);
    }
}
