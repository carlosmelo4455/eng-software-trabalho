package org.biblioteca.domain.transacao.emprestimo;

import org.biblioteca.config.service.BaseService;
import org.biblioteca.domain.usuario.Usuario;

public interface EmprestimoService extends BaseService<Emprestimo, Long> {

    Emprestimo buscarEmprestimoPorCodigoUsuarioECodigoLivro(String codigoUsuario, String codigoLivro);

    void verificarUsuarioComEmprestimoAtrasado(String codigoUsuario);

    void verificarSeUsuarioPodeEmprestarMaisLivros(Usuario usuario);

    void verificarSeEmprestimoEstaAtivo(String codigoUsuario, String codigoLivro);

    Emprestimo buscarEmprestimoPorExemplar(String codigoExemplar);
}
