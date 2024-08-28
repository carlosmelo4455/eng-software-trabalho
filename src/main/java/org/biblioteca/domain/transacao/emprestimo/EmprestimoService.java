package org.biblioteca.domain.transacao.emprestimo;

import org.biblioteca.config.service.BaseService;
import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.transacao.reserva.Reserva;
import org.biblioteca.domain.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface EmprestimoService extends BaseService<Emprestimo, Long> {

    Emprestimo buscarEmprestimoPorCodigoUsuarioECodigoLivro(String codigoUsuario, String codigoLivro);

    List<Emprestimo> buscarEmprestimosPorCodigoUsuario(String codigoUsuario);

    void verificarUsuarioComEmprestimoAtrasado(String codigoUsuario);

    void verificarSeUsuarioPodeEmprestarMaisLivros(Usuario usuario);

    void verificarSeEmprestimoEstaAtivo(String codigoUsuario, String codigoLivro);
}
