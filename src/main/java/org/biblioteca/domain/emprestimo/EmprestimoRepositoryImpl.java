package org.biblioteca.domain.emprestimo;

import org.biblioteca.config.repository.CacheRepositoryImpl;
import org.biblioteca.config.singleton.Singleton;
import org.biblioteca.config.singleton.SingletonManager;

@Singleton
public class EmprestimoRepositoryImpl extends CacheRepositoryImpl<Emprestimo, Long> implements EmprestimoRepository {

    private EmprestimoRepositoryImpl() {
    }

    public static EmprestimoRepositoryImpl getInstance() {
        return SingletonManager.getInstance(EmprestimoRepositoryImpl.class);
    }
}