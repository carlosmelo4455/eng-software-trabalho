package org.biblioteca.domain.usuario;

import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

import java.util.List;
import java.util.Optional;

@Singleton
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository = UsuarioRepositoryImpl.getInstance();

    private UsuarioServiceImpl() {
    }

    public static UsuarioService getInstance() {
        return SingletonManager.getInstance(UsuarioServiceImpl.class);
    }

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorCodigo(String codigo) {
        return usuarioRepository.findById(codigo);
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario atualizarUsuario(Usuario usuarioAtualizado) {
        return usuarioRepository.update(usuarioAtualizado);
    }

    @Override
    public void removerUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}
