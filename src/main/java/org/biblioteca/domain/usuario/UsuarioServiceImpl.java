package org.biblioteca.domain.usuario;

import org.biblioteca.config.service.AbstractService;

public class UsuarioServiceImpl extends AbstractService<Usuario, String> implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
    }
}
