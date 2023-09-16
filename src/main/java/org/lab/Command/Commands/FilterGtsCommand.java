package org.lab.Command.Commands;

import org.lab.DataClasses.Worker;
import org.lab.Command.ICommand;

import java.util.LinkedList;

/**
 * Выводит элементы, значение поля salary которых больше заданного.
 */
public class FilterGtsCommand implements ICommand {
    private final String info;

    public FilterGtsCommand(String info) {
        this.info = info;
    }

    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {

        Float salary = null;
        try {
            salary = Float.parseFloat(args);
        }
            catch (NumberFormatException e){
            System.out.println("Invalid data type. Example: 1500.99.");
        }

        for (Worker w : WorkersData) {
            try {
                if(w.getSalary() > salary)
                    System.out.println(w);
                }
            catch (NullPointerException e){
                if(w.getSalary() == null)
                    System.out.printf("The salary field is not set for id: %s.\n", w.getId());
            }
        }

        return WorkersData;
    }

    @Override
    public String getName() {
        return "filter_greater_than_salary";
    }

    @Override
    public String getHelp() {
        return info;
    }
}
