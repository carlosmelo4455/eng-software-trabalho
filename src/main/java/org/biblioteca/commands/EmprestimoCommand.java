//package org.biblioteca.commands;
//
//public class EmprestimoCommand implements Command {
//    private final EmprestimoService emprestimoService;
//    private final String codigoUsuario;
//    private final String codigoLivro;
//
//    public EmprestimoCommand(EmprestimoService emprestimoService, String codigoUsuario, String codigoLivro) {
//        this.emprestimoService = emprestimoService;
//        this.codigoUsuario = codigoUsuario;
//        this.codigoLivro = codigoLivro;
//    }
//
//
//    @Override
//    public void execute() {
//        emprestimoService.emprestar(codigoUsuario, codigoLivro);
//    }
//}
