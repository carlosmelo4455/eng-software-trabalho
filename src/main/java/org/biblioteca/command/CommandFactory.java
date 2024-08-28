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

        return switch (comandoEnum) {
            case EMPRESTIMO -> new EmprestimoCommand(bibliotecaFacade, codigoUsuario, codigoLivro);
            case RESERVA -> new ReservaCommand(bibliotecaFacade, codigoUsuario, codigoLivro);
            case DEVOLUCAO -> new DevolucaoCommand(bibliotecaFacade, codigoUsuario, codigoLivro);
            case OBSERVACAO -> new ObservacaoCommand(bibliotecaFacade, codigoUsuario, codigoLivro);
            case INFORMACOES_LIVRO -> new InformacoesLivroCommand(bibliotecaFacade, codigoLivro);
            case INFORMACOES_USUARIO -> new InformacoesUsuarioCommand(bibliotecaFacade, codigoUsuario);
            case NOTIFICACOES -> new NotificacoesCommand(bibliotecaFacade, codigoUsuario);
            case SAIR -> new SairCommand();
        };
    }
}