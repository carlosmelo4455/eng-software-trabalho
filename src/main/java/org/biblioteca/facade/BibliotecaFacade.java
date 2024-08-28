package org.biblioteca.facade;

import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.exemplar.ExemplarService;
import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.historico.Acao;
import org.biblioteca.domain.historico.Historico;
import org.biblioteca.domain.historico.HistoricoService;
import org.biblioteca.domain.transacao.emprestimo.Emprestimo;
import org.biblioteca.domain.transacao.emprestimo.EmprestimoService;
import org.biblioteca.domain.transacao.reserva.Reserva;
import org.biblioteca.domain.transacao.reserva.ReservaService;
import org.biblioteca.domain.usuario.Usuario;
import org.biblioteca.domain.usuario.UsuarioService;
import org.biblioteca.exception.LivroNaoEncontradoException;
import org.biblioteca.exception.UsuarioNaoEncontradoException;
import org.biblioteca.observer.LivroObserver;
import org.biblioteca.observer.Observer;
import org.biblioteca.strategy.EmprestimoComReservaStrategy;
import org.biblioteca.strategy.EmprestimoSemReservaStrategy;
import org.biblioteca.strategy.EmprestimoStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BibliotecaFacade {
    private final static int LIMITE_RESERVAS = 3;
    private final Map<String, Observer> observadores = new HashMap<>();


    private final EmprestimoService emprestimoService;
    private final ReservaService reservaService;
    private final UsuarioService usuarioService;
    private final ExemplarService exemplarService;
    private final HistoricoService historicoService;

    public BibliotecaFacade(EmprestimoService emprestimoService, ReservaService reservaService, UsuarioService usuarioService, ExemplarService exemplarService, HistoricoService historicoService) {
        this.emprestimoService = emprestimoService;
        this.reservaService = reservaService;
        this.usuarioService = usuarioService;
        this.exemplarService = exemplarService;
        this.historicoService = historicoService;
    }

    public void emprestarLivro(String codigoUsuario, String codigoLivro) throws Exception {
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        Livro livro = buscarLivroPorCodigo(codigoLivro);

        emprestimoService.verificarUsuarioComEmprestimoAtrasado(codigoUsuario);
        emprestimoService.verificarSeUsuarioPodeEmprestarMaisLivros(usuario);
        emprestimoService.verificarSeEmprestimoEstaAtivo(codigoUsuario, codigoLivro);

        List<Exemplar> exemplaresDisponiveis = exemplarService.buscarExemplaresDisponiveisPorCodigoLivro(codigoLivro);

        Reserva reserva = reservaService.buscarReservaPorCodigoUsuarioECodigoLivro(codigoUsuario, codigoLivro).orElse(null);
        EmprestimoStrategy strategy = selecionarStrategy(reserva);
        Emprestimo emprestimo = strategy.emprestar(usuario, livro, exemplaresDisponiveis, reserva);

        emprestimoService.adicionar(emprestimo);
        historicoService.salvarNoHistorico(emprestimo, Acao.SOLICITACAO_EMPRESTIMO);
        System.out.println("Empréstimo realizado com sucesso: " + emprestimo.getUsuario().getNome() + " pegou emprestado o livro : " + emprestimo.getExemplar().getLivro().getTitulo());
    }

    public void reservarLivro(String codigoUsuario, String codigoLivro) {
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);

        reservaService.verificarLimiteReservas(codigoUsuario, LIMITE_RESERVAS);
        reservaService.verificarSeUsuarioTemReservaAtiva(codigoUsuario, codigoLivro);
        emprestimoService.verificarSeEmprestimoEstaAtivo(codigoUsuario, codigoLivro);

        Reserva reserva = new Reserva(usuario, livro);

        reservaService.adicionar(reserva);
        livro.registrarReserva();
        historicoService.salvarNoHistorico(reserva, Acao.SOLICITACAO_RESERVA);

        System.out.println("Reserva realizada com sucesso: " + usuario.getNome() + " reservou o livro :" + livro.getTitulo());
    }

    public void devolverLivro(String codigoUsuario, String codigoLivro) {
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        Emprestimo emprestimo = emprestimoService.buscarEmprestimoPorCodigoUsuarioECodigoLivro(codigoUsuario, codigoLivro);

        Exemplar exemplar = emprestimo.getExemplar();
        emprestimoService.remover(emprestimo);
        exemplar.setDisponivel(true);
        exemplarService.atualizar(exemplar);
        historicoService.salvarNoHistorico(emprestimo, Acao.DEVOLUCAO_EMPRESTIMO);

        System.out.println("Livro devolvido com sucesso: " + usuario.getNome() + " devolveu " + livro.getTitulo());
    }

    public void adicionarObservacao(String codigoUsuario, String codigoLivro) {
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        Observer observer = new LivroObserver(codigoUsuario);
        livro.registrarObserver(observer);
        observadores.put(codigoUsuario, observer);
        System.out.println("Observador adicionado com sucesso: " + observer.getClass().getSimpleName());
    }

    public void consultarInformacoesLivro(String codigoLivro) {
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Quantidade de Reservas: " + livro.getReservas());

        if (livro.getReservas() > 0) {
            System.out.println("Usuários que realizaram reservas:");
            historicoService.buscarHistoricosPorUsuarioEAcao(codigoLivro, Acao.SOLICITACAO_RESERVA)
                    .forEach(historico -> System.out.println("- " + historico.getUsuario().getNome()));
        }

        System.out.println("Exemplares:");

        exemplarService.buscarExemplaresPorCodigoLivro(codigoLivro).forEach(exemplar -> {
            System.out.print("Código: " + exemplar.getId() + ", Status: ");
            if (exemplar.isDisponivel()) {
                System.out.println("Disponível");
            } else {
                historicoService.buscarHistoricosPorUsuarioEAcao(exemplar.getId(), Acao.SOLICITACAO_EMPRESTIMO).forEach(historico -> {
                    if (historico.getAcao() == Acao.DEVOLUCAO_EMPRESTIMO) {
                        System.out.println("Emprestado para " + historico.getUsuario().getNome() +
                                ", Data de Empréstimo: " + historico.getDataTransacao() +
                                ", Data Prevista para Devolução: " + historico.getDataTransacao());
                    }
                });
            }
        });
        for (Exemplar exemplar : exemplarService.buscarExemplaresPorCodigoLivro(codigoLivro)) {
            System.out.print("Código: " + exemplar.getId() + ", Status: ");
            if (exemplar.isDisponivel()) {
                System.out.println("Disponível");
            } else {
                List<Historico> historicosEmprestimos = historicoService.buscarHistoricosPorUsuarioEAcao(exemplar.getId(), Acao.SOLICITACAO_EMPRESTIMO);
                for (Historico historico : historicosEmprestimos) {
                    if (historico.getAcao() == Acao.DEVOLUCAO_EMPRESTIMO) {
                        System.out.println("Emprestado para " + historico.getUsuario().getNome() +
                                ", Data de Empréstimo: " + historico.getDataTransacao() +
                                ", Data Prevista para Devolução: " + historico.getDataTransacao());
                    }
                }
            }
        }
    }

    public void consultarInformacoesUsuario(String codigoUsuario) {
        System.out.println("Empréstimos:");
        List<Historico> historicosEmprestimos = historicoService.buscarHistoricosPorUsuarioEAcao(codigoUsuario, Acao.SOLICITACAO_EMPRESTIMO);
        historicosEmprestimos.forEach(historico -> {
            String status = historico.getAcao() == Acao.DEVOLUCAO_EMPRESTIMO ? "Finalizado" : "Em curso";
            String dataDevolucao = historico.getAcao() == Acao.DEVOLUCAO_EMPRESTIMO ? historico.getDataTransacao().toString() : "N/A";
            System.out.println("Título: " + historico.getLivro().getTitulo() +
                    ", Data do Empréstimo: " + historico.getDataTransacao() +
                    ", Status: " + status +
                    ", Data de Devolução: " + dataDevolucao);
        });

        System.out.println("Reservas:");
        List<Historico> historicosReservas = historicoService.buscarHistoricosPorUsuarioEAcao(codigoUsuario, Acao.SOLICITACAO_RESERVA);
        historicosReservas.forEach(historico -> System.out.println("Título: " + historico.getLivro().getTitulo() +
                ", Data da Solicitação: " + historico.getDataTransacao()));
    }

    public void consultarNotificacoes(String codigoUsuario) {
        Observer observer = observadores.get(codigoUsuario);
        if (observer != null) {
            System.out.println("Notificações para o usuário " + codigoUsuario + ": " + observer.getNotificacoes());
        } else {
            System.out.println("Nenhuma notificação para o usuário " + codigoUsuario);
        }
    }

    private Usuario buscarUsuarioPorCodigo(String codigoUsuario) {
        Usuario usuario = usuarioService.buscarPorId(codigoUsuario);
        if (usuario == null) {
            throw new UsuarioNaoEncontradoException();
        }
        return usuario;
    }

    private Livro buscarLivroPorCodigo(String codigoLivro) {
        return exemplarService.buscarLivroPorCodigo(codigoLivro).orElseThrow(LivroNaoEncontradoException::new);
    }

    private EmprestimoStrategy selecionarStrategy(Reserva reserva) {
        if (reserva != null) {
            return new EmprestimoComReservaStrategy(reservaService, exemplarService, historicoService);
        } else {
            return new EmprestimoSemReservaStrategy(exemplarService, reservaService);
        }
    }
}