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
            case EMPRESTIMO -> criarEmprestimoCommand(codigoUsuario, codigoLivro);
            case RESERVA -> criarReservaCommand(codigoUsuario, codigoLivro);
            case DEVOLUCAO -> criarDevolucaoCommand(codigoUsuario, codigoLivro);
            case OBSERVACAO -> criarObservacaoCommand(codigoUsuario, codigoLivro);
            case INFORMACOES_LIVRO -> criarInformacoesLivroCommand(codigoLivro);
            case INFORMACOES_USUARIO -> criarInformacoesUsuarioCommand(codigoUsuario);
            case NOTIFICACOES -> criarNotificacoesCommand(codigoUsuario);
            case SAIR -> criarSairCommand();
        };
    }

    private Command criarEmprestimoCommand(String codigoUsuario, String codigoLivro) {
        return new EmprestimoCommand(bibliotecaFacade, codigoUsuario, codigoLivro);
    }

    private Command criarReservaCommand(String codigoUsuario, String codigoLivro) {
        return new ReservaCommand(bibliotecaFacade, codigoUsuario, codigoLivro);
    }

    private Command criarDevolucaoCommand(String codigoUsuario, String codigoLivro) {
        return new DevolucaoCommand(bibliotecaFacade, codigoUsuario, codigoLivro);
    }

    private Command criarObservacaoCommand(String codigoUsuario, String codigoLivro) {
        return new ObservacaoCommand(bibliotecaFacade, codigoUsuario, codigoLivro);
    }

    private Command criarInformacoesLivroCommand(String codigoLivro) {
        return new InformacoesLivroCommand(bibliotecaFacade, codigoLivro);
    }

    private Command criarInformacoesUsuarioCommand(String codigoUsuario) {
        return new InformacoesUsuarioCommand(bibliotecaFacade, codigoUsuario);
    }

    private Command criarNotificacoesCommand(String codigoUsuario) {
        return new NotificacoesCommand(bibliotecaFacade, codigoUsuario);
    }

    private Command criarSairCommand() {
        return new SairCommand();
    }
}