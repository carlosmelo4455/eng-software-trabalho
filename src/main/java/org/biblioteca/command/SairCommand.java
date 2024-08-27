package org.biblioteca.command;

public class SairCommand implements Command {
    @Override
    public void execute() {
        System.exit(0);
    }
}
