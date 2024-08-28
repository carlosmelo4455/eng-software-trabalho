package org.biblioteca.domain.transacao.emprestimo;

import org.biblioteca.config.service.AbstractService;
import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.historico.Acao;
import org.biblioteca.domain.transacao.reserva.Reserva;
import org.biblioteca.domain.usuario.Usuario;
import org.biblioteca.exception.EmprestimoAtivoException;
import org.biblioteca.exception.EmprestimoNaoEncontradoException;
import org.biblioteca.exception.LimiteEmprestimoExcedidoException;
import org.biblioteca.exception.UsuarioComEmprestimoEmAtrasoException;
import org.biblioteca.strategy.EmprestimoComReservaStrategy;
import org.biblioteca.strategy.EmprestimoSemReservaStrategy;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EmprestimoServiceImpl extends AbstractService<Emprestimo, Long> implements EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoServiceImpl(EmprestimoRepository emprestimoRepository) {
        super(emprestimoRepository);
        this.emprestimoRepository = emprestimoRepository;
    }

    @Override
    public Emprestimo buscarEmprestimoPorCodigoUsuarioECodigoLivro(String codigoUsuario, String codigoLivro) {
        return emprestimoRepository.findEmprestimoPorCodigoUsuarioAndCodigoLivro(codigoUsuario, codigoLivro).orElseThrow(EmprestimoNaoEncontradoException::new);
    }

    @Override
    public List<Emprestimo> buscarEmprestimosPorCodigoUsuario(String codigoUsuario) {
        return emprestimoRepository.buscarEmprestimosPorCodigoUsuario(codigoUsuario);
    }

    @Override
    public void verificarUsuarioComEmprestimoAtrasado(String codigoUsuario) {
        List<Emprestimo> emprestimos = emprestimoRepository.buscarEmprestimosPorCodigoUsuario(codigoUsuario);
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucao().isBefore(LocalDate.now())) {
                throw new UsuarioComEmprestimoEmAtrasoException();
            }
        }
    }

    @Override
    public void verificarSeUsuarioPodeEmprestarMaisLivros(Usuario usuario) {
        List<Emprestimo> emprestimos = emprestimoRepository.buscarEmprestimosPorCodigoUsuario(usuario.getId());
        if (usuario.getLimiteEmprestimos() < emprestimos.size()){
            throw new LimiteEmprestimoExcedidoException();
        }
    }

    @Override
    public void verificarSeEmprestimoEstaAtivo(String codigoUsuario, String codigoLivro) {
        if (emprestimoRepository.findEmprestimoPorCodigoUsuarioAndCodigoLivro(codigoUsuario, codigoLivro).isPresent()){
            throw new EmprestimoAtivoException();
        }
    }

}