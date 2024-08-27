package org.biblioteca.command;

import org.biblioteca.facade.BibliotecaFacade;

public class InformacoesLivroCommand implements Command {
    private final BibliotecaFacade bibliotecaFacade;
    private final String codigoLivro;

    public InformacoesLivroCommand(BibliotecaFacade bibliotecaFacade, String codigoLivro) {
        this.bibliotecaFacade = bibliotecaFacade;
        this.codigoLivro = codigoLivro;
    }

    @Override
    public void execute() {
        bibliotecaFacade.consultarInformacoesLivro(codigoLivro);
    }
}
