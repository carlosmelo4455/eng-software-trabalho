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

import java.util.List;

public class BibliotecaFacade {
    private final static int LIMITE_RESERVAS = 3; // Definir a constante para o limite de reservas


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
        System.out.println("Empréstimo realizado com sucesso: " + emprestimo.getUsuario().getNome() + " pegou emprestado " + emprestimo.getExemplar().getLivro().getTitulo());
    }

    public void reservarLivro(String codigoUsuario, String codigoLivro) {
        Livro livro = buscarLivroPorCodigo(codigoLivro);
        Usuario usuario = buscarUsuarioPorCodigo(codigoUsuario);

        reservaService.verificarLimiteReservas(codigoUsuario, LIMITE_RESERVAS);
        reservaService.verificarSeUsuarioTemReservaAtiva(codigoUsuario, codigoLivro);
        emprestimoService.verificarSeEmprestimoEstaAtivo(codigoUsuario, codigoLivro);

        Reserva reserva = new Reserva(usuario, livro);

        reservaService.adicionar(reserva);
        historicoService.salvarNoHistorico(reserva, Acao.SOLICITACAO_RESERVA);

        System.out.println("Reserva realizada com sucesso: " + usuario.getNome() + " reservou " + livro.getTitulo());
    }

    public void devolverLivro(String codigoUsuario, String codigoLivro) throws Exception {
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
    }

    public void consultarInformacoesLivro(String codigoLivro) {
    }

    public void consultarInformacoesUsuario(String codigoUsuario) {
    }

    public void consultarNotificacoes(String codigoUsuario) {

    }

    private Usuario buscarUsuarioPorCodigo(String codigoUsuario){
        Usuario usuario = usuarioService.buscarPorId(codigoUsuario);
        if (usuario == null) {
            throw new UsuarioNaoEncontradoException();
        }
        return usuario;
    }

    private Livro buscarLivroPorCodigo(String codigoLivro){
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