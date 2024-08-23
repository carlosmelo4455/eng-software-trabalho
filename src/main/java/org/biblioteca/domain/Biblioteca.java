package org.biblioteca.domain;

import org.biblioteca.domain.emprestimo.Emprestimo;
import org.biblioteca.domain.emprestimo.EmprestimoService;
import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.exemplar.ExemplarService;
import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.historico.HistoricoService;
import org.biblioteca.domain.reserva.Reserva;
import org.biblioteca.domain.reserva.ReservaService;
import org.biblioteca.domain.usuario.Usuario;
import org.biblioteca.domain.usuario.UsuarioService;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.nonNull;

public class Biblioteca {
    private final UsuarioService usuarioService;
    private final ExemplarService exemplarService;
    private final EmprestimoService emprestimoService;
    private final ReservaService reservaService;
    private final HistoricoService historicoService;

    public Biblioteca(UsuarioService usuarioService, ExemplarService exemplarService, EmprestimoService emprestimoService, ReservaService reservaService, HistoricoService historicoService) {
        this.usuarioService = usuarioService;
        this.exemplarService = exemplarService;
        this.emprestimoService = emprestimoService;
        this.reservaService = reservaService;
        this.historicoService = historicoService;
    }

    public void emprestarLivro(String codigoUsuario, String codigoLivro) throws Exception {
        // verificar se o livro existe no banco de dados
        Livro livro = buscarLivro(codigoLivro);

        // verificar se o usuario existe
        Usuario usuario = buscarUsuario(codigoUsuario);

        //verificar se o usuario esta devendo a entrega de um exemplar
        List<Emprestimo> emprestimos = emprestimoService.buscarEmprestimosPorCodigoUsuario(codigoUsuario);
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucao().isAfter(LocalDate.now())) {
                throw new Exception("Usuário está devendo a entrega de um exemplar.");
            }
        }

        //verificar o limite de emprestimos do usuario, professores podem pegar quantos livros quiserem,
        if (usuario.getLimiteEmprestimos() <= emprestimos.size()) {
            throw new Exception("Usuário atingiu o limite de empréstimos.");
        }

        //verificar se o usuario nao tem um emprestimo ativo do mesmo livro
        if (emprestimoService.buscarEmprestimoPorCodigoUsuarioECodigoLivro(codigoUsuario, codigoLivro).isPresent()) {
            throw new Exception("Usuário já possui um empréstimo ativo do livro.");
        }


        List<Exemplar> exemplaresDisponiveis = exemplarService.buscarExemplaresDisponiveisPorCodigoLivro(codigoLivro);
        if (exemplaresDisponiveis.isEmpty()) {
            throw new Exception("Não há exemplares disponíveis do livro.");
        }
            // verificar se o usuario já tem uma reserva para esse livro,
            // se tiver, empresta o livro. a reserva é removida e o livro emprestado, salvo no historico a transação
        Reserva reserva = reservaService.buscarReservaPorCodigoUsuarioECodigoLivro(codigoUsuario, codigoLivro).orElse(null);
        if (nonNull(reserva)) {
            reservaService.remover(reserva);
            Exemplar exemplar = exemplaresDisponiveis.get(0);
            exemplar.setDisponivel(false);
            exemplarService.atualizar(exemplar);
            emprestimoService.adicionar(new Emprestimo(usuario, exemplar));
            historicoService.salvarNoHistorico(new Emprestimo(usuario, exemplar));
            historicoService.salvarNoHistorico(reserva);
            System.out.println("Empréstimo realizado com sucesso: " + usuario.getNome() + " pegou emprestado " + livro.getTitulo());
        }else {
            List<Reserva> reservas = reservaService.buscarReservasPorCodigoLivro(codigoLivro);

            //caso nao tenha reserva, verificar a quantidade de reservas para esse livro menor que a quantidade
            // de exemplares disponiveis, se sim, emprestar o livro. alem disso, se for um professor, emprestar o livro
            if (reservas.size() < exemplaresDisponiveis.size()) {
                Exemplar exemplar = exemplaresDisponiveis.get(0);
                exemplar.setDisponivel(false);
                exemplarService.atualizar(exemplar);
                emprestimoService.adicionar(new Emprestimo(usuario, exemplar));
                historicoService.salvarNoHistorico(new Emprestimo(usuario, exemplar));
                System.out.println("Empréstimo realizado com sucesso: " + usuario.getNome() + " pegou emprestado " + livro.getTitulo());
            } else {
                if (usuario.podePegarEmprestado()){
                    Exemplar exemplar = exemplaresDisponiveis.get(0);
                    exemplar.setDisponivel(false);
                    exemplarService.atualizar(exemplar);
                    emprestimoService.adicionar(new Emprestimo(usuario, exemplar));
                    historicoService.salvarNoHistorico(new Emprestimo(usuario, exemplar));
                    System.out.println("Empréstimo realizado com sucesso: " + usuario.getNome() + " pegou emprestado " + livro.getTitulo());
                }
                else {
                    throw new Exception("Não há exemplares disponíveis do livro.");
                }
            }
        }
    }

    public void reservarLivro(String codigoUsuario, String codigoLivro) {
        try {
            Livro livro = buscarLivro(codigoLivro);

            Usuario usuario = buscarUsuario(codigoUsuario);

            // verificar se o usuario tem menos de 3 reservas ativas, se não tiver, nao pode reservar
            if (reservaService.contarReservasPorUsuario(codigoUsuario) >= 3) {
                throw new Exception("Usuário já possui 3 reservas ativas.");
            }

            // verificar se o usuario ja tem um emprestimo ativo do livro
            if (emprestimoService.buscarEmprestimoPorCodigoUsuarioECodigoLivro(codigoUsuario, codigoLivro).isPresent()) {
                throw new Exception("Usuário já possui um empréstimo ativo do livro.");
            }

            // verificar se o usuario ja tem uma reserva ativa do livro
            if (reservaService.buscarReservaPorCodigoUsuarioECodigoLivro(codigoUsuario, codigoLivro).isPresent()) {
                throw new Exception("Usuário já possui uma reserva ativa do livro.");
            }

            // salvar reserva e salvar no historico
            reservaService.adicionar(new Reserva(usuario, livro));
            historicoService.salvarNoHistorico(new Reserva(usuario, livro));

            // no fim, printar uma mensagem de sucesso e insucesso explicando o motivo
            System.out.println("Reserva realizada com sucesso: " + usuario.getNome() + " reservou " + livro.getTitulo());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void devolverLivro(String codigoUsuario, String codigoLivro) {
        try {
            Livro livro = buscarLivro(codigoLivro);

            Usuario usuario = buscarUsuario(codigoUsuario);

            Emprestimo emprestimo = buscarEmprestimo(codigoUsuario, codigoLivro);

            Exemplar exemplar = emprestimo.getExemplar();
            exemplar.setDisponivel(true);
            emprestimoService.remover(emprestimo);
            exemplarService.atualizar(exemplar);
            historicoService.salvarNoHistorico(emprestimo);

            System.out.println("Livro devolvido com sucesso: " + usuario.getNome() + " devolveu " + livro.getTitulo());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void registrarObservacaoLivro(String codigoUsuario, String codigoLivro) {
        // verificar se o usuario existe

        // verificar se o usuario é observador, mas nao pode verificar se é uma instancia mockada desse cara, usar interface
        // ou alguma coisa pra definir isso

        // verificar se o usuario tem uma reserva ativa do livro

        // no fim, printar uma mensagem de sucesso e insucesso explicando o motivo
    }

    public void verificarInformacoesLivro(String codigoLivro) {
        // verificar se o livro existe

        // mostrar titulo, quantidade de reservas, e se diferente de zero, mostrar quem realizou cada reserva.
        // para cada exemplar no sistema, mostrar codigo, status, e se tiver emprestado, exibir o usuario que emprestou, com
        // data do emprestimo e data da devolução.
    }

    public void verificarInformacoesUsuario(String codigoUsuario) {
        // verificar se o usuario existe

        // mostrar nome, quantidade de emprestimos no historico, e listar no historico a quantidade de reservas
        // as reservas devem apresentar titulo do livro reservado e a data da solicitacao.
    }

    public void verificarNotificacoes(String codigoUsuario) {
        // verificar se o usuario existe

        // retornar a quantidade de vezes que ele foi notificado sobre mais de duas reservaa simultaneas em livros observados
        // por ele
    }

    private Livro buscarLivro(String codigoLivro) throws Exception {
        return exemplarService.buscarLivroPorCodigo(codigoLivro).orElseThrow(() -> new Exception("Livro não encontrado."));
    }

    private Usuario buscarUsuario(String codigoUsuario) throws Exception {
        return usuarioService.buscarPorId(codigoUsuario).orElseThrow(() -> new Exception("Usuário não encontrado."));
    }

    private Emprestimo buscarEmprestimo(String codigoUsuario, String codigoLivro) throws Exception {
        return emprestimoService.buscarEmprestimoPorCodigoUsuarioECodigoLivro(codigoUsuario, codigoLivro).orElseThrow(() -> new Exception("Emprestimo não encontrado."));
    }
}
