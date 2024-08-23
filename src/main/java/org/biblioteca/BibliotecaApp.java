package org.biblioteca;

import org.biblioteca.domain.emprestimo.Emprestimo;
import org.biblioteca.domain.emprestimo.EmprestimoRepository;
import org.biblioteca.domain.emprestimo.EmprestimoRepositoryImpl;
import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.exemplar.ExemplarRepository;
import org.biblioteca.domain.exemplar.ExemplarRepositoryImpl;
import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.reserva.Reserva;
import org.biblioteca.domain.reserva.ReservaRepository;
import org.biblioteca.domain.reserva.ReservaRepositoryImpl;
import org.biblioteca.domain.usuario.*;

public class BibliotecaApp {
    public static void main(String[] args) {
        AlunoGraduacao joao = new AlunoGraduacao("123", "João da Silva");
        AlunoPosGraduacao luis = new AlunoPosGraduacao("456", "Luis Fernando Rodrigues");
        AlunoGraduacao pedro = new AlunoGraduacao("789", "Pedro Paulo");
        Professor carlos = new Professor("100", "Carlos Lucena");

        UsuarioRepository usuarioRepository = UsuarioRepositoryImpl.getInstance();
        usuarioRepository.save(joao);
        usuarioRepository.save(luis);
        usuarioRepository.save(pedro);
        usuarioRepository.save(carlos);

        Livro livro1 = new Livro("100", "Engenharia de Software", "AddisonWesley", new String[]{"Ian Sommervile"}, "6ª", 2000);
        Livro livro2 = new Livro("101", "UML – Guia do Usuário", "Campus", new String[]{"Grady Booch", "James Rumbaugh", "Ivar Jacobson"}, "7ª", 2000);
        Livro livro3 = new Livro("200", "Code Complete", "Microsoft Press", new String[]{"Steve McConnell"}, "2ª", 2014);
        Livro livro4 = new Livro("201", "Agile Software Development, Principles, Patterns, and Practices", "Prentice Hall", new String[]{"Robert Martin"}, "1ª", 2002);
        Livro livro5 = new Livro("300", "Refactoring: Improving the Design of Existing Code", "Addison-Wesley Professional", new String[]{"Martin Fowler"}, "1ª", 1999);
        Livro livro6 = new Livro("301", "Software Metrics: A Rigorous and Practical Approach", "CRC Press", new String[]{"Norman Fenton", "James Bieman"}, "3ª", 2014);
        Livro livro7 = new Livro("400", "Design Patterns: Elements of Reusable Object-Oriented Software", "Addison-Wesley Professional", new String[]{"Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides"}, "1ª", 1994);
        Livro livro8 = new Livro("401", "UML Distilled: A Brief Guide to the Standard Object Modeling Language", "Addison-Wesley Professional", new String[]{"Martin Fowler"}, "3ª", 2003);

        Exemplar exemplar1 = new Exemplar(livro1, "01");
        Exemplar exemplar2 = new Exemplar(livro1, "02");
        Exemplar exemplar3 = new Exemplar(livro2, "03");
        Exemplar exemplar4 = new Exemplar(livro3, "04");
        Exemplar exemplar5 = new Exemplar(livro4, "05");
        Exemplar exemplar6 = new Exemplar(livro5, "06");
        Exemplar exemplar7 = new Exemplar(livro5, "07");
        Exemplar exemplar8 = new Exemplar(livro7, "08");
        Exemplar exemplar9 = new Exemplar(livro7, "09");

        Emprestimo emprestimo = new Emprestimo(joao, exemplar1);
        Emprestimo emprestimo2 = new Emprestimo(luis, exemplar2);

        ExemplarRepository exemplarRepository = ExemplarRepositoryImpl.getInstance();
        exemplarRepository.save(exemplar1);
        exemplarRepository.save(exemplar2);
        exemplarRepository.save(exemplar3);
        exemplarRepository.save(exemplar4);
        exemplarRepository.save(exemplar5);
        exemplarRepository.save(exemplar6);
        exemplarRepository.save(exemplar7);
        exemplarRepository.save(exemplar8);
        exemplarRepository.save(exemplar9);

        EmprestimoRepository emprestimoRepository = EmprestimoRepositoryImpl.getInstance();
        emprestimoRepository.save(emprestimo);
        emprestimoRepository.save(emprestimo2);

        ReservaRepository reservaRepository = ReservaRepositoryImpl.getInstance();

        Reserva reserva1 = new Reserva(pedro, livro2);
        Reserva reserva2 = new Reserva(carlos, livro3);

        reservaRepository.save(reserva1);
        reservaRepository.save(reserva2);

        Emprestimo emprestimo3 = new Emprestimo(joao, exemplar4);

        //TODO: realizar emprestimo, salvar lista de exemplares, realizar devolução, reservar livros.
    }
}