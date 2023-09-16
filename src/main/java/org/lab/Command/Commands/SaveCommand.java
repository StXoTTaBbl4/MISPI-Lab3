package org.lab.Command.Commands;

import org.lab.Command.ICommand;
import org.lab.DataClasses.Worker;
import org.lab.CollectionInit.ZonedDateTimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.LinkedList;

/**
 * Сохраняет коллекцию в файл.
 */
public class SaveCommand implements ICommand {
    private final String info;

    public SaveCommand(String info) {
        this.info = info;
    }


    @Override
    public LinkedList<Worker> handle(String path, LinkedList<Worker> WorkersData) {

        Path path2file = Paths.get(path);
        if(!Files.exists(path2file)){
            try {
                Files.createFile(path2file);
            } catch (IOException e) {
                System.out.println("Such a file already exists.");
            }
        }

        Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer())
                        .create();
        try(Writer fw = new OutputStreamWriter(new FileOutputStream(path))){
                fw.write(gson.toJson(WorkersData));
                fw.flush();
            } catch (AccessDeniedException | FileNotFoundException e) {
            System.out.println("Access error, unable to write.");
        }catch (IOException e) {
            e.printStackTrace();
        }

        return WorkersData;
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getHelp() {
        return info;
    }
}
