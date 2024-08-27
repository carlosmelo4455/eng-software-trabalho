package org.biblioteca.command;

import org.biblioteca.facade.BibliotecaFacade;

public class EmprestimoCommand implements Command {
    private final BibliotecaFacade bibliotecaFacade;
    private final String codigoUsuario;
    private final String codigoLivro;

    public EmprestimoCommand(BibliotecaFacade bibliotecaFacade, String codigoUsuario, String codigoLivro) {
        this.bibliotecaFacade = bibliotecaFacade;
        this.codigoUsuario = codigoUsuario;
        this.codigoLivro = codigoLivro;
    }

    @Override
    public void execute() throws Exception {
        bibliotecaFacade.emprestarLivro(codigoUsuario, codigoLivro);
    }
}
