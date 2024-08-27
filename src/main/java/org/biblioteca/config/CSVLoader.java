// CSVLoader.java
package org.biblioteca.config;

import org.biblioteca.domain.exemplar.Exemplar;
import org.biblioteca.domain.exemplar.Livro;
import org.biblioteca.domain.usuario.AlunoGraduacao;
import org.biblioteca.domain.usuario.AlunoPosGraduacao;
import org.biblioteca.domain.usuario.Professor;
import org.biblioteca.domain.usuario.Usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    private static final String USUARIOS_CSV = "/csv/usuarios.csv";
    private static final String LIVROS_CSV = "/csv/livros.csv";
    private static final String EXEMPLARES_CSV = "/csv/exemplares.csv";
    private static final String CSV_SPLIT_BY = ";";

    public static List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                CSVLoader.class.getResourceAsStream(USUARIOS_CSV)))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                Usuario usuario = criarUsuario(line.split(CSV_SPLIT_BY));
                if (usuario != null) {
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public static List<Exemplar> carregarExemplares() {
        List<Livro> livros = carregarLivros();
        List<Exemplar> exemplares = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                CSVLoader.class.getResourceAsStream(EXEMPLARES_CSV)))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                Exemplar exemplar = criarExemplar(line.split(CSV_SPLIT_BY), livros);
                if (exemplar != null) {
                    exemplares.add(exemplar);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exemplares;
    }

    private static Usuario criarUsuario(String[] usuarioData) {
        String id = usuarioData[0];
        String nome = usuarioData[1];
        String tipo = usuarioData[2];

        return switch (tipo) {
            case "AlunoGraduacao" -> new AlunoGraduacao(id, nome);
            case "AlunoPosGraduacao" -> new AlunoPosGraduacao(id, nome);
            case "Professor" -> new Professor(id, nome);
            default -> null;
        };
    }

    private static List<Livro> carregarLivros() {
        List<Livro> livros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                CSVLoader.class.getResourceAsStream(LIVROS_CSV)))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                Livro livro = criarLivro(line.split(CSV_SPLIT_BY));
                livros.add(livro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return livros;
    }

    private static Livro criarLivro(String[] livroData) {
        String id = livroData[0];
        String titulo = livroData[1];
        String editora = livroData[2];
        String[] autores = livroData[3].split(",");
        String edicao = livroData[4];
        int ano = Integer.parseInt(livroData[5]);

        return new Livro(id, titulo, editora, autores, edicao, ano);
    }

    private static Exemplar criarExemplar(String[] exemplarData, List<Livro> livros) {
        String idLivro = exemplarData[0];
        String codigo = exemplarData[1];

        Livro livro = livros.stream()
                .filter(l -> l.getCodigo().equals(idLivro))
                .findFirst()
                .orElse(null);

        return (livro != null) ? new Exemplar(livro, codigo) : null;
    }
}