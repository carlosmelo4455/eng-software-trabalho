package org.biblioteca.domain.usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario cadastrarUsuario(Usuario usuario);

    Optional<Usuario> buscarUsuarioPorCodigo(String codigo);

    List<Usuario> listarTodosUsuarios();

    Usuario atualizarUsuario(Usuario usuarioAtualizado);

    void removerUsuario(Usuario usuario);
}
