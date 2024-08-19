package org.biblioteca.domain.usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario adicionar(Usuario usuario);

    Optional<Usuario> buscarPorId(String codigo);

    List<Usuario> listarTodos();

    Usuario atualizar(Usuario usuarioAtualizado);

    void remover(Usuario usuario);
}
