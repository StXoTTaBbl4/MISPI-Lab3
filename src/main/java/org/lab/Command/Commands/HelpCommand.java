package org.lab.Command.Commands;

import org.lab.Command.CommandManager;
import org.lab.Command.ICommand;
import org.lab.DataClasses.Worker;

import java.util.LinkedList;

/**
 * Выводит справку по доступным командам.
 */
public class HelpCommand implements ICommand {
    private final String info;
    private final CommandManager manager;

    /** Запрашивает существующий commandManager для возможности вызова у них методов help и getName.
     *
     * @param manager CommandManager для доступа к методам других команд.
     */
    public HelpCommand( CommandManager manager,String info) {
        this.manager = manager;
        this.info = info;
    }

    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkersData) {

        if(args.isEmpty()){
            StringBuilder builder = new StringBuilder();

            builder.append("Command List:\n");

            manager.getCommands().stream().map(ICommand::getName).forEach((it) -> builder.append(it).append('\n'));
            System.out.println(builder);
        }else {

            ICommand command = manager.getCommand(args);

            if (command == null) {
                System.out.printf("Command + %s not found.\n", args);
            }else{
                System.out.println(command.getHelp());
            }
        }
        return WorkersData;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return info;
    }
}
