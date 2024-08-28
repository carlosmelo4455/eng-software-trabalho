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
        String codigo1 = partes.length > 1 ? partes[1] : null;
        String codigo2 = partes.length > 2 ? partes[2] : null;

        ComandoEnum comandoEnum = ComandoEnum.fromString(tipoComando);
        if (comandoEnum == null) {
            return null;
        }

        return switch (comandoEnum) {
            case EMPRESTIMO -> new EmprestimoCommand(bibliotecaFacade, codigo1, codigo2);
            case RESERVA -> new ReservaCommand(bibliotecaFacade, codigo1, codigo2);
            case DEVOLUCAO -> new DevolucaoCommand(bibliotecaFacade, codigo1, codigo2);
            case OBSERVACAO -> new ObservacaoCommand(bibliotecaFacade, codigo1, codigo2);
            case INFORMACOES_LIVRO -> new InformacoesLivroCommand(bibliotecaFacade, codigo1);
            case INFORMACOES_USUARIO -> new InformacoesUsuarioCommand(bibliotecaFacade, codigo1);
            case NOTIFICACOES -> new NotificacoesCommand(bibliotecaFacade, codigo1);
            case SAIR -> new SairCommand();
        };
    }
}