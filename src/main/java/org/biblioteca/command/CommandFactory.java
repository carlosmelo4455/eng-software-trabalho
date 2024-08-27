// CommandFactory.java
package org.biblioteca.command;

import org.biblioteca.facade.BibliotecaFacade;

public class CommandFactory {
    private final BibliotecaFacade bibliotecaFacade;

    public CommandFactory(BibliotecaFacade bibliotecaFacade) {
        this.bibliotecaFacade = bibliotecaFacade;
    }

    public Command criarComando(String input) {
        String[] partes = input.split(" ");
        String tipoComando = partes[0];
        String codigoUsuario = partes.length > 1 ? partes[1] : null;
        String codigoLivro = partes.length > 2 ? partes[2] : null;

        ComandoEnum comandoEnum = ComandoEnum.fromString(tipoComando);
        if (comandoEnum == null) {
            return null;
        }

        try {
            return comandoEnum.getCommandClass()
                    .getConstructor(BibliotecaFacade.class, String.class, String.class)
                    .newInstance(bibliotecaFacade, codigoUsuario, codigoLivro);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}