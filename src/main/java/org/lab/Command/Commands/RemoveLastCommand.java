package org.lab.Command.Commands;

import org.lab.DataClasses.Worker;
import org.lab.Command.ICommand;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Удаляет последний элемент из коллекции.
 */
public class RemoveLastCommand implements ICommand {
    private final String info;

    public RemoveLastCommand(String info) {
        this.info = info;
    }

    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {

        try {
            WorkersData.removeLast();
        }catch (NoSuchElementException e){
            System.out.println("Коллекция пуста.");
        }

        return WorkersData;
    }

    @Override
    public String getName() {
        return "remove_last";
    }

    @Override
    public String getHelp() {
        return info;
    }
}
