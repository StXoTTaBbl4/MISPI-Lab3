package org.lab.Command.Commands;

import org.lab.Command.ICommand;
import org.lab.DataClasses.Worker;

import java.util.LinkedList;

/**
 * Завершает программу (без сохранения в файл).
 */
public class ExitCommand implements ICommand {
    private final String info;

    public ExitCommand(String info) {
        this.info = info;
    }

    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {
        System.exit(0);
        return WorkersData;
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getHelp() {
        return info;
    }
}
