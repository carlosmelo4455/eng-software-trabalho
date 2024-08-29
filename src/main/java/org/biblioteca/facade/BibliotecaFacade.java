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

import java.time.format.DateTimeFormatter;
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
    DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);

        if (usuario.podeObservar()) {
            Observer observer = new LivroObserver(codigoUsuario);
            livro.registrarObserver(observer);
            observadores.put(codigoUsuario, observer);
            System.out.println("Observador adicionado com sucesso: " + usuario.getNome() + " está observando " + livro.getTitulo());
        } else {
            System.out.println("Usuário não tem permissão para observar livros");
        }
    }

    public void consultarInformacoesLivro(String codigoLivro) {
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        System.out.println("Título: " + livro.getTitulo());
        List<Reserva> reservas = reservaService.buscarReservasPorCodigoLivro(codigoLivro);
        System.out.println("Quantidade de Reservas: " + livro.getReservas());

        if (!reservas.isEmpty()) {
            System.out.println("Usuários que realizaram reservas para o livro");
            reservas.forEach(reserva -> System.out.println("Nome: " + reserva.getUsuario().getNome() + ", Data da Reserva: " + reserva.getDataTransacao().format(FORMATO_DATA)));
        }

        System.out.println("Exemplares:");

        exemplarService.buscarExemplaresPorCodigoLivro(codigoLivro).forEach(exemplar -> {
            System.out.print("Código: " + exemplar.getId() + ", Status: ");
            if (exemplar.isDisponivel()) {
                System.out.println("Disponível");
            } else {
                Emprestimo emprestimo = emprestimoService.buscarEmprestimoPorExemplar(exemplar.getId());
                System.out.println("Emprestado para " + emprestimo.getUsuario().getNome() +
                        ", Data de Empréstimo: " + emprestimo.getDataTransacao().format(FORMATO_DATA) +
                        ", Data Prevista para Devolução: " + emprestimo.getDataDevolucao().format(FORMATO_DATA));
            }
        });
    }

    public void consultarInformacoesUsuario(String codigoUsuario) {
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        System.out.println("Nome: " + usuario.getNome());
        List<Historico> historicosEmprestimos = historicoService.buscarHistoricosEmprestimoPorUsuario(codigoUsuario);
        System.out.println("Empréstimos:");
        if (historicosEmprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo encontrado");
        }
        historicosEmprestimos.forEach(historico -> {
            System.out.println("Empréstimos:");
            String status = historico.getAcao() == Acao.DEVOLUCAO_EMPRESTIMO ? "Finalizado" : "Em curso";
            String dataDevolucao = historico.getAcao() == Acao.DEVOLUCAO_EMPRESTIMO ? historico.getDataTransacao().format(FORMATO_DATA) : "N/A";
            System.out.println("Título: " + historico.getLivro().getTitulo() +
                    ", Data do Empréstimo: " + historico.getDataTransacao().format(FORMATO_DATA) +
                    ", Status: " + status +
                    ", Data de Devolução: " + dataDevolucao);
        });

        System.out.println("Reservas:");
        List<Historico> historicosReservas = historicoService.buscarHistoricosReservaPorUsuario(codigoUsuario);
        if (historicosReservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada");
        }
        historicosReservas.forEach(historico -> {
            String status = historico.getAcao() == Acao.SOLICITACAO_RESERVA ? "Ativa" : "Finalizada";
            System.out.println("Título: " + historico.getLivro().getTitulo() +
                    ", Data da Solicitação: " + historico.getDataTransacao().format(FORMATO_DATA) + ", Status: " + status);
        });
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