package org.biblioteca.facade;

import org.biblioteca.model.Livro;
import org.biblioteca.model.Usuario;
import org.biblioteca.services.interfaces.EmprestimoService;
import org.biblioteca.services.interfaces.LivroService;
import org.biblioteca.services.interfaces.ReservaService;
import org.biblioteca.services.interfaces.UsuarioService;

import java.util.List;

public class BibliotecaFacade {

    private final ReservaService reservaService;
    private final EmprestimoService emprestimoService;
    private final LivroService livroService;
    private final UsuarioService usuarioService;

    public BibliotecaService(ReservaService reservaService, EmprestimoService emprestimoService, LivroService livroService, UsuarioService usuarioService) {
        this.reservaService = reservaService;
        this.emprestimoService = emprestimoService;
        this.livroService = livroService;
        this.usuarioService = usuarioService;
    }

    public void realizarReserva(Usuario usuario, Livro livro) {
        reservaService.realizarReserva(usuario, livro);
    }

    public void emprestarLivro(Usuario usuario, Livro livro) {
        emprestimoService.emprestarLivro(usuario, livro);
    }

    public void devolverLivro(Usuario usuario, Livro livro) {
        emprestimoService.devolverLivro(usuario, livro);
    }

    public List<Livro> listarLivrosDisponiveis() {
        return livroService.listarLivrosDisponiveis();
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }

}