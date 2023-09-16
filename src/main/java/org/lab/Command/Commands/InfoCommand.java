package org.lab.Command.Commands;

import org.lab.Command.ICommand;
import org.lab.DataClasses.Worker;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.LinkedList;

/**
 * Выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов).
 */
public class InfoCommand  implements ICommand {
    private final String info;
    private final String path;

    /**
     *
     * @param path Путь к файлу коллекции.
     */
    public InfoCommand(String info, String path) {
        this.info = info;
        this.path = path;
    }

    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {
        File file = new File(path);
        Date d = new Date(file.lastModified());
        DateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("Type: " + WorkersData.getClass().toString().replace("class","")+ "\n" +
                           "Last modified date: "+ dateParser.format(d) + "\n" +
                           "Number of elements: " + WorkersData.size() + "\n");

        return WorkersData;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getHelp() {
        return info;
    }
}
