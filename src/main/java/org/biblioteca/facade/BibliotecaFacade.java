package org.biblioteca.facade;

import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.exemplar.ExemplarService;
import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.historico.Acao;
import org.biblioteca.domain.historico.HistoricoService;
import org.biblioteca.domain.transacao.emprestimo.Emprestimo;
import org.biblioteca.domain.transacao.emprestimo.EmprestimoService;
import org.biblioteca.domain.transacao.reserva.Reserva;
import org.biblioteca.domain.transacao.reserva.ReservaService;
import org.biblioteca.domain.usuario.Usuario;
import org.biblioteca.domain.usuario.UsuarioService;
import org.biblioteca.exception.*;
import org.biblioteca.strategy.EmprestimoComReservaStrategy;
import org.biblioteca.strategy.EmprestimoSemReservaStrategy;
import org.biblioteca.strategy.EmprestimoStrategy;

import java.time.LocalDate;
import java.util.List;

public class BibliotecaFacade {
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
        verificarPendenciasUsuario(codigoUsuario, codigoLivro);

        List<Exemplar> exemplaresDisponiveis = buscarExemplaresDisponiveis(codigoLivro);
        Reserva reserva = buscarReserva(codigoUsuario, codigoLivro);
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        Livro livro = buscarLivroPorCodigo(codigoLivro);

        EmprestimoStrategy strategy = selecionarStrategy(reserva);
        Emprestimo emprestimo = strategy.emprestar(usuario, livro, exemplaresDisponiveis, reserva);

        processarEmprestimo(emprestimo);
    }

    public void reservarLivro(String codigoUsuario, String codigoLivro) throws Exception {
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        verificarLimiteReservas(codigoUsuario);
        verificarEmprestimoAtivo(codigoUsuario, codigoLivro);
        verificarReservaAtiva(codigoUsuario, codigoLivro);

        Reserva reserva = new Reserva(usuario, livro);
        reservaService.adicionar(reserva);
        historicoService.salvarNoHistorico(reserva, Acao.SOLICITACAO_RESERVA);

        System.out.println("Reserva realizada com sucesso: " + usuario.getNome() + " reservou " + livro.getTitulo());
    }

    public void devolverLivro(String codigoUsuario, String codigoLivro) throws Exception {
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        Emprestimo emprestimo = buscarEmprestimo(codigoUsuario, codigoLivro);

        Exemplar exemplar = emprestimo.getExemplar();
        exemplar.setDisponivel(true);
        emprestimoService.remover(emprestimo);
        exemplarService.atualizar(exemplar);
        historicoService.salvarNoHistorico(emprestimo, Acao.DEVOLUCAO_EMPRESTIMO);

        System.out.println("Livro devolvido com sucesso: " + usuario.getNome() + " devolveu " + livro.getTitulo());
    }

    public void adicionarObservacao(String codigoUsuario, String codigoLivro) {
    }

    public void consultarInformacoesLivro(String codigoLivro) {
    }

    public void consultarInformacoesUsuario(String codigoUsuario) {
    }

    public void consultarNotificacoes(String codigoUsuario) {

    }

    private Usuario buscarUsuarioPorCodigo(String codigoUsuario){
        return usuarioService.buscarPorId(codigoUsuario).orElseThrow(UsuarioNaoEncontradoException::new);
    }

    private Livro buscarLivroPorCodigo(String codigoLivro){
        return exemplarService.buscarLivroPorCodigo(codigoLivro).orElseThrow(LivroNaoEncontradoException::new);
    }

    private void verificarPendenciasUsuario(String codigoUsuario, String codigoLivro) throws Exception {
        verificarEmprestimosAtrasados(codigoUsuario);
        verificarLimiteEmprestimos(codigoUsuario);
        verificarEmprestimoAtivo(codigoUsuario, codigoLivro);
    }

    private void verificarEmprestimosAtrasados(String codigoUsuario) {
        List<Emprestimo> emprestimos = emprestimoService.buscarEmprestimosPorCodigoUsuario(codigoUsuario);
        emprestimos.forEach(emprestimo -> {
            if (emprestimo.getDataDevolucao().isAfter(LocalDate.now())) {
                throw new UsuarioComEmprestimoEmAtrasoException();
            }
        });
    }

    private void verificarLimiteEmprestimos(String codigoUsuario){
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);
        List<Emprestimo> emprestimos = emprestimoService.buscarEmprestimosPorCodigoUsuario(codigoUsuario);
        if (usuario.getLimiteEmprestimos() <= emprestimos.size()) {
            throw new LimiteEmprestimoExcedidoException();
        }
    }

    private void verificarEmprestimoAtivo(String codigoUsuario, String codigoLivro) {
        if (emprestimoService.buscarEmprestimoPorCodigoUsuarioECodigoLivro(codigoUsuario, codigoLivro).isPresent()) {
            throw new EmprestimoAtivoException();
        }
    }
    private void verificarLimiteReservas(String codigoUsuario) throws Exception {
        if (reservaService.contarReservasPorUsuario(codigoUsuario) >= 3) {
            throw new Exception("Usuário já possui 3 reservas ativas.");
        }
    }

    private void verificarReservaAtiva(String codigoUsuario, String codigoLivro) throws Exception {
        if (reservaService.buscarReservaPorCodigoUsuarioECodigoLivro(codigoUsuario, codigoLivro).isPresent()) {
            throw new Exception("Usuário já possui uma reserva ativa do livro.");
        }
    }

    private List<Exemplar> buscarExemplaresDisponiveis(String codigoLivro) throws Exception {
        List<Exemplar> exemplaresDisponiveis = exemplarService.buscarExemplaresDisponiveisPorCodigoLivro(codigoLivro);
        if (exemplaresDisponiveis.isEmpty()) {
            throw new Exception("Não há exemplares disponíveis do livro.");
        }
        return exemplaresDisponiveis;
    }

    private Reserva buscarReserva(String codigoUsuario, String codigoLivro){
        return reservaService.buscarReservaPorCodigoUsuarioECodigoLivro(codigoUsuario, codigoLivro).orElse(null);
    }

    private EmprestimoStrategy selecionarStrategy(Reserva reserva) {
        if (reserva != null) {
            return new EmprestimoComReservaStrategy(reservaService, exemplarService, emprestimoService, historicoService);
        } else {
            return new EmprestimoSemReservaStrategy(exemplarService, reservaService);
        }
    }

    private Emprestimo buscarEmprestimo(String codigoUsuario, String codigoLivro) throws Exception {
        return emprestimoService.buscarEmprestimoPorCodigoUsuarioECodigoLivro(codigoUsuario, codigoLivro).orElseThrow(() -> new Exception("Empréstimo não encontrado."));
    }

    private void processarEmprestimo(Emprestimo emprestimo){
        emprestimoService.adicionar(emprestimo);
        historicoService.salvarNoHistorico(emprestimo, Acao.SOLICITACAO_EMPRESTIMO);
        System.out.println("Empréstimo realizado com sucesso: " + emprestimo.getUsuario().getNome() + " pegou emprestado " + emprestimo.getExemplar().getLivro().getTitulo());
    }
}