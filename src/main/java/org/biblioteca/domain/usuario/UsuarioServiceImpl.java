package org.biblioteca.domain.usuario;

import org.biblioteca.config.service.AbstractService;

public class UsuarioServiceImpl extends AbstractService<Usuario, String> implements UsuarioService {

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
    }
}
