package org.biblioteca.config.injector;

import org.biblioteca.command.CommandFactory;
import org.biblioteca.controller.BibliotecaController;
import org.biblioteca.domain.exemplar.ExemplarService;
import org.biblioteca.domain.exemplar.ExemplarServiceImpl;
import org.biblioteca.domain.historico.HistoricoService;
import org.biblioteca.domain.historico.HistoricoServiceImpl;
import org.biblioteca.domain.transacao.emprestimo.EmprestimoService;
import org.biblioteca.domain.transacao.emprestimo.EmprestimoServiceImpl;
import org.biblioteca.domain.transacao.reserva.ReservaService;
import org.biblioteca.domain.transacao.reserva.ReservaServiceImpl;
import org.biblioteca.domain.usuario.UsuarioService;
import org.biblioteca.domain.usuario.UsuarioServiceImpl;
import org.biblioteca.facade.BibliotecaFacade;

public class DependencyInjector {

    private static final EmprestimoService emprestimoService = EmprestimoServiceImpl.getInstance();
    private static final ReservaService reservaService = ReservaServiceImpl.getInstance();
    private static final HistoricoService historicoService = HistoricoServiceImpl.getInstance();
    private static final UsuarioService usuarioService = UsuarioServiceImpl.getInstance();
    private static final ExemplarService exemplarService = ExemplarServiceImpl.getInstance();
    private static final BibliotecaFacade bibliotecaFacade = new BibliotecaFacade(emprestimoService, reservaService, usuarioService, exemplarService, historicoService);
    private static final CommandFactory commandFactory = new CommandFactory(bibliotecaFacade);
    private static final BibliotecaController bibliotecaController = new BibliotecaController(commandFactory);

    public static BibliotecaController getBibliotecaController() {
        return bibliotecaController;
    }
}
