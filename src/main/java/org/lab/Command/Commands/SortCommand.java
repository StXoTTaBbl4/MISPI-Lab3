package org.lab.Command.Commands;

import org.lab.Command.ICommand;
import org.lab.DataClasses.Worker;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Сортирует коллекцию в естественном порядке(по ID).
 */
public class SortCommand implements ICommand {
    private final String info;

    public SortCommand(String info) {
        this.info = info;
    }

    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {

        try {
            Collections.sort(WorkersData);
        }catch (NullPointerException e){
            System.out.println("Sorting error, checking the fields Coordinates, Person, Salary. They must not be null.");
        }


        return WorkersData;
    }

    @Override
    public String getName() {
        return "sort";
    }

    @Override
    public String getHelp() {
        return info;
    }
}
