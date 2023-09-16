package org.lab.Command;

import org.lab.Command.Commands.*;
import org.lab.DataClasses.Worker;
import org.lab.Command.Commands.AddIfMax.AddIfMaxCommand;

import java.util.*;

/**
 * Класс, который отвечает за обработку пользовательских команд и передачу их дальше для исполнения.
 */
public class CommandManager {

    private final List<ICommand> commands = new ArrayList<>();
    ResourceBundle resourceBundle = ResourceBundle.getBundle("Info", Locale.getDefault());

    /**
     * @return Список доступных команд.
     */
    public List<ICommand> getCommands() {
        return commands;
    }

    /** Конструктор, в который добавляются все объекты классов-команд с помощью {@link CommandManager#addCommand(ICommand)}.
     *
     * @param path Путь к файлу, содержащему коллекцию.
     */
    public CommandManager(String path){
        addCommand(new AddCommand(resourceBundle.getString("add.info")));
        addCommand(new AddIfMaxCommand(this,resourceBundle.getString("addIfMax.info")));
        addCommand(new ClearCommand(resourceBundle.getString("clean.info")));
        addCommand(new CountGtpCommand(resourceBundle.getString("countGtp.info")));
        addCommand(new ExecuteScriptCommand(this,resourceBundle.getString("executeScript.info")));
        addCommand(new ExitCommand(resourceBundle.getString("exit.info")));
        addCommand(new FilterGtsCommand(resourceBundle.getString("filterGts.info")));
        addCommand(new GroupCbsCommand(resourceBundle.getString("groupCbs.info")));
        addCommand(new HelpCommand(this,resourceBundle.getString("help.info")));
        addCommand(new InfoCommand(path,resourceBundle.getString("info.info")));
        addCommand(new RemoveIdCommand(resourceBundle.getString("removeID.info")));
        addCommand(new RemoveLastCommand(resourceBundle.getString("removeLast.info")));
        addCommand(new SaveCommand(resourceBundle.getString("save.info")));
        addCommand(new ShowCommand(resourceBundle.getString("show.info")));
        addCommand(new SortCommand(resourceBundle.getString("sort.info")));
        addCommand(new UpdateIdCommand(resourceBundle.getString("update.info")));
    }

    /** Внутренний метод для добавления команды в общий список доступных команд.
     *
     * @param cmd Объект класса-команды
     */
    private void addCommand(ICommand cmd){
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));
        if(nameFound){
            throw new IllegalArgumentException("Command: "+cmd.getName()+" already exists");
        }
        commands.add(cmd);
    }

    /** Метод для поиска команды по ее имени.
     *
     * @param search имя искомой команды.
     * @return В случае существования команды возвращает объект класса-команды.
     */
    public ICommand getCommand(String search){
        String searchLower = search.toLowerCase();

        for (ICommand cmd: this.commands) {
            if(cmd.getName().equals(searchLower)){
                return cmd;
            }
        }
        return null;
    }

    /** Метод, передающий параметры для исполнения команды методу handle объекта класса-команды.
     *
     * @param input Строка, содержащая има команды и аргументы.
     * @param WorkerData Коллекция, с которой взаимодействует программа.
     * @return Коллекция после ее обработки в соответствии с командой.
     */
    public LinkedList<Worker> CommandHandler(String input, LinkedList<Worker> WorkerData){
        String[] data = input.split(" ");

        ICommand cmd  = this.getCommand(data[0]);

        if(cmd != null){
            List<String> args = Arrays.asList(data).subList(1, data.length);

              WorkerData = cmd.handle(args.toString().substring(1,args.toString().length()-1), WorkerData);
        } else if (input.equals("exit")) {
            System.exit(0);
        } else{
            System.out.println("Command not found!\n");
        }
        return WorkerData;
    }
}
