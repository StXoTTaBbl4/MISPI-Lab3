package org.lab.Command.Commands;

import org.lab.Command.CommandManager;
import org.lab.Command.ICommand;
import org.lab.DataClasses.Worker;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Считывает и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
 */
public class ExecuteScriptCommand implements ICommand {
    private final String info;
    private final CommandManager manager;

    /** Запрашивает существующий commandManager для возможности вызова у них методов help и getName.
     *
     * @param manager CommandManager для доступа к методу handle команд.
     */
    public ExecuteScriptCommand(CommandManager manager, String info) {
        this.manager = manager;
        this.info = info;
    }

    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {

        Scanner scanner = null;
        LinkedList<Worker> localList = WorkersData;

        if(args.equals("")){
            System.out.println("You must specify the path to the file.");
            System.exit(0);
        }
        else {
            try {
                scanner = new Scanner(new FileInputStream(args));
            } catch (FileNotFoundException e) {
                System.out.println("The specified file does not exist.");
                return WorkersData;
            }
        }

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            System.out.println("Command execution: " + line + ".\n");
            localList = manager.CommandHandler(line, localList);
        }

        System.out.println("""
                Script completed:
                accept result - 1,
                cancel - 2,
                show result - 3.""");

        while(true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                switch (reader.readLine()) {
                    case "1" -> {
                        return localList;
                    }
                    case "2" -> {
                        return WorkersData;
                    }
                    case "3" -> {
                        for (Worker w : localList) {
                            System.out.println(w.toString());
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getHelp() {
        return info;
    }
}
