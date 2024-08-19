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
    public Usuario adicionar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(String codigo) {
        return usuarioRepository.findById(codigo);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario atualizar(Usuario usuarioAtualizado) {
        return usuarioRepository.update(usuarioAtualizado);
    }

    @Override
    public void remover(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}
