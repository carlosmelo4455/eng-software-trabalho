package org.biblioteca.domain.usuario;

import org.biblioteca.config.repository.CacheRepositoryImpl;
import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

@Singleton
public class UsuarioRepositoryImpl extends CacheRepositoryImpl<Usuario, String> implements UsuarioRepository {

    private UsuarioRepositoryImpl() {
    }

    public static UsuarioRepositoryImpl getInstance() {
        return SingletonManager.getInstance(UsuarioRepositoryImpl.class);
    }
}
