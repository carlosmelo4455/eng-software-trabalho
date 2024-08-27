package org.biblioteca;

import org.biblioteca.config.CSVLoader;
import org.biblioteca.config.injector.DependencyInjector;
import org.biblioteca.controller.BibliotecaController;
import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.exemplar.ExemplarRepository;
import org.biblioteca.domain.exemplar.ExemplarRepositoryImpl;
import org.biblioteca.domain.usuario.Usuario;
import org.biblioteca.domain.usuario.UsuarioRepository;
import org.biblioteca.domain.usuario.UsuarioRepositoryImpl;

import java.util.List;

public class BibliotecaApp {
    public static void main(String[] args) throws Exception {
        UsuarioRepository usuarioRepository = UsuarioRepositoryImpl.getInstance();
        ExemplarRepository exemplarRepository = ExemplarRepositoryImpl.getInstance();

        List<Usuario> usuarios = CSVLoader.carregarUsuarios();
        usuarios.forEach(usuarioRepository::save);

        List<Exemplar> exemplares = CSVLoader.carregarExemplares();
        exemplares.forEach(exemplarRepository::save);

        BibliotecaController bibliotecaController = DependencyInjector.getBibliotecaController();
        bibliotecaController.iniciar();
    }
}