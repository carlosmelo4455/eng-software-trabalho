package org.biblioteca.config.injector;

import org.biblioteca.command.CommandFactory;
import org.biblioteca.controller.BibliotecaController;
import org.biblioteca.domain.exemplar.ExemplarRepository;
import org.biblioteca.domain.exemplar.ExemplarRepositoryImpl;
import org.biblioteca.domain.exemplar.ExemplarService;
import org.biblioteca.domain.exemplar.ExemplarServiceImpl;
import org.biblioteca.domain.historico.HistoricoRepository;
import org.biblioteca.domain.historico.HistoricoRepositoryImpl;
import org.biblioteca.domain.historico.HistoricoService;
import org.biblioteca.domain.historico.HistoricoServiceImpl;
import org.biblioteca.domain.transacao.emprestimo.EmprestimoRepository;
import org.biblioteca.domain.transacao.emprestimo.EmprestimoRepositoryImpl;
import org.biblioteca.domain.transacao.emprestimo.EmprestimoService;
import org.biblioteca.domain.transacao.emprestimo.EmprestimoServiceImpl;
import org.biblioteca.domain.transacao.reserva.ReservaRepository;
import org.biblioteca.domain.transacao.reserva.ReservaRepositoryImpl;
import org.biblioteca.domain.transacao.reserva.ReservaService;
import org.biblioteca.domain.transacao.reserva.ReservaServiceImpl;
import org.biblioteca.domain.usuario.UsuarioRepository;
import org.biblioteca.domain.usuario.UsuarioRepositoryImpl;
import org.biblioteca.domain.usuario.UsuarioService;
import org.biblioteca.domain.usuario.UsuarioServiceImpl;
import org.biblioteca.facade.BibliotecaFacade;

public class DependencyInjector {

    private static final EmprestimoRepository emprestimoRepository = EmprestimoRepositoryImpl.getInstance();
    private static final ReservaRepository reservaRepository = ReservaRepositoryImpl.getInstance();
    private static final HistoricoRepository historicoRepository = HistoricoRepositoryImpl.getInstance();
    private static final UsuarioRepository usuarioRepository = UsuarioRepositoryImpl.getInstance();
    private static final ExemplarRepository exemplarRepository = ExemplarRepositoryImpl.getInstance();

    private static final EmprestimoService emprestimoService = new EmprestimoServiceImpl(emprestimoRepository);
    private static final ReservaService reservaService = new ReservaServiceImpl(reservaRepository);
    private static final HistoricoService historicoService = new HistoricoServiceImpl(historicoRepository);
    private static final UsuarioService usuarioService = new UsuarioServiceImpl(usuarioRepository);
    private static final ExemplarService exemplarService = new ExemplarServiceImpl(exemplarRepository);

    private static final BibliotecaFacade bibliotecaFacade = new BibliotecaFacade(emprestimoService, reservaService, usuarioService, exemplarService, historicoService);
    private static final CommandFactory commandFactory = new CommandFactory(bibliotecaFacade);
    private static final BibliotecaController bibliotecaController = new BibliotecaController(commandFactory);

    public static BibliotecaController getBibliotecaController() {
        return bibliotecaController;
    }
}
