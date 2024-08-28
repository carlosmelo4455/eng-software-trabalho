package org.biblioteca;

import org.biblioteca.config.injector.DependencyInjector;
import org.biblioteca.controller.BibliotecaController;
import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.exemplar.ExemplarRepository;
import org.biblioteca.domain.exemplar.ExemplarRepositoryImpl;
import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.usuario.*;

public class BibliotecaApp {
    public static void main(String[] args) throws Exception {
        inicializarDados();
        BibliotecaController bibliotecaController = DependencyInjector.getBibliotecaController();
        bibliotecaController.iniciar();
    }

    private static void inicializarDados() {
        UsuarioRepository usuarioRepository = UsuarioRepositoryImpl.getInstance();
        usuarioRepository.save(new AlunoGraduacao("123", "João da Silva"));
        usuarioRepository.save(new AlunoPosGraduacao("456", "Luis Fernando Rodrigues"));
        usuarioRepository.save(new AlunoGraduacao("789", "Pedro Paulo"));
        usuarioRepository.save(new Professor("100", "Carlos Lucena"));

        Livro[] livros = {
                new Livro("100", "Engenharia de Software", "AddisonWesley", new String[]{"Ian Sommervile"}, "6ª", 2000),
                new Livro("101", "UML – Guia do Usuário", "Campus", new String[]{"Grady Booch", "James Rumbaugh", "Ivar Jacobson"}, "7ª", 2000),
                new Livro("200", "Code Complete", "Microsoft Press", new String[]{"Steve McConnell"}, "2ª", 2014),
                new Livro("201", "Agile Software Development, Principles, Patterns, and Practices", "Prentice Hall", new String[]{"Robert Martin"}, "1ª", 2002),
                new Livro("300", "Refactoring: Improving the Design of Existing Code", "Addison-Wesley Professional", new String[]{"Martin Fowler"}, "1ª", 1999),
                new Livro("301", "Software Metrics: A Rigorous and Practical Approach", "CRC Press", new String[]{"Norman Fenton", "James Bieman"}, "3ª", 2014),
                new Livro("400", "Design Patterns: Elements of Reusable Object-Oriented Software", "Addison-Wesley Professional", new String[]{"Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides"}, "1ª", 1994),
                new Livro("401", "UML Distilled: A Brief Guide to the Standard Object Modeling Language", "Addison-Wesley Professional", new String[]{"Martin Fowler"}, "3ª", 2003)
        };

        Exemplar[] exemplares = {
                new Exemplar(livros[0], "01"), new Exemplar(livros[0], "02"),
                new Exemplar(livros[1], "03"), new Exemplar(livros[2], "04"),
                new Exemplar(livros[3], "05"), new Exemplar(livros[4], "06"),
                new Exemplar(livros[4], "07"), new Exemplar(livros[6], "08"),
                new Exemplar(livros[6], "09")
        };

        ExemplarRepository exemplarRepository = ExemplarRepositoryImpl.getInstance();
        for (Exemplar exemplar : exemplares) {
            exemplarRepository.save(exemplar);
        }
    }
}