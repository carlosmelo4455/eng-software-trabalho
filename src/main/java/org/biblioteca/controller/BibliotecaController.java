// BibliotecaController.java
package org.biblioteca.controller;

import org.biblioteca.command.Command;
import org.biblioteca.command.CommandFactory;

import java.util.Scanner;

public class BibliotecaController {
    private final CommandFactory commandFactory;

    public BibliotecaController(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public void iniciar(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo à Biblioteca. Digite seu comando:");

        while (true) {
            String input = scanner.nextLine();
            Command comando = commandFactory.criarComando(input);
            if (comando != null) {
                try {
                    comando.execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Comando não reconhecido. Tente novamente.");
            }
        }
    }
}