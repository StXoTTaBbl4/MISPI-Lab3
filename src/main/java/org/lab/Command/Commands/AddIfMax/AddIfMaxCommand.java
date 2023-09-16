package org.lab.Command.Commands.AddIfMax;

import org.lab.Command.CommandManager;
import org.lab.Command.ICommand;
import org.lab.DataClasses.Worker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
 */
public class AddIfMaxCommand implements ICommand {
    private final String info;
    private final CommandManager manager;

    public AddIfMaxCommand(CommandManager manager, String info) {
        this.manager = manager;
        this.info = info;
    }

    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {

        AddIfMaxComparator addIfMaxComparator = new AddIfMaxComparator();

        Collections.sort(WorkersData);
        LinkedList<Worker> newWorker = new LinkedList<>();
        Worker worker = manager.CommandHandler("add " + args.replaceAll(",",""),newWorker).get(0);

        try{
            worker.setId(WorkersData.getLast().getId()+1);
        }
        catch (NoSuchElementException e){
            worker.setId(1);
            WorkersData.add(worker);
            return WorkersData;
        }

        WorkersData.sort(addIfMaxComparator);
        try {
            if(addIfMaxComparator.compare(worker,WorkersData.getLast()) > 0)
                WorkersData.add(worker);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Список пуст, не с чем сравнивать.");
        }

        return WorkersData;
    }

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getHelp() {
        return info;
    }

//    @Override
//    public String getHelp() {
//        return "Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
//    }
}
