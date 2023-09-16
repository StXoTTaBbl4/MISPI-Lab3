package org.lab.Command.Commands;

import org.lab.Command.ICommand;
import org.lab.DataClasses.Worker;

import java.util.LinkedList;

/**
 * Очищает коллекцию.
 */
public class ClearCommand implements ICommand {
    private final String info;

    public ClearCommand(String info) {
        this.info = info;
    }

    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {


        return new LinkedList<>();
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getHelp() {
        return info;
    }
}
