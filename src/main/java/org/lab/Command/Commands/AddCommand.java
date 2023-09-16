package org.lab.Command.Commands;

import org.lab.Command.ICommand;
import org.lab.DataClasses.Coordinates;
import org.lab.DataClasses.Person;
import org.lab.DataClasses.Position;
import org.lab.DataClasses.Worker;

import java.io.IOException;
import java.time.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Добавляет новый элемент в коллекцию.
 */
public class AddCommand implements ICommand {
    private final String info;

    public AddCommand(String info) {
        this.info = info;
    }

    public AddCommand(){
        this.info = "";
    }

    @Override
    public LinkedList<Worker> handle(String args, LinkedList<Worker> WorkerData) {

        Collections.sort(WorkerData);
        String[] userData = args.split(" ");
        for (int i = 0; i < userData.length; i++) {
            userData[i] = userData[i].trim();
        }

        if(userData.length < 13){
            System.out.println("Not all options are specified.");
            return WorkerData;
        }

        Worker worker;
        int id;
        String name;
        Coordinates coordinates;
        float salary;
        LocalDate startDate;
        LocalDateTime endDate = null;
        Position position = null;
        Person person = new Person();

        try{
            id = WorkerData.getLast().getId()+1;
        }
        catch (NoSuchElementException e){
            id = 1;
        }

        name = userData[0];
        if(name.equals("\"\""))
            System.out.println("The name field cannot be empty.");

        try {
            Float x = Float.parseFloat(userData[1]);
            Double y = Double.parseDouble(userData[2]);
            coordinates = new Coordinates(x, y);
        }
        catch (NumberFormatException e) {
            System.out.println("Datatype error for Coordinates(x/y). Fields cannot be null.");
            return WorkerData;
        }

        try {
            salary = Float.parseFloat(userData[3]);
        }
        catch (NumberFormatException e) {
            System.out.println("Salary field data type error. The field cannot be null.");
            return WorkerData;
        }

        try {
            String[] stData = userData[4].split("-");
            startDate = LocalDate.of(Integer.parseInt(stData[0]),
                    Integer.parseInt(stData[1]),
                    Integer.parseInt(stData[2]));
        }
        catch (DateTimeException | NumberFormatException e) {
            System.out.println("Error in startDate data, example: 2000-10-15. Field cannot be null");
            return WorkerData;
        }

        try {
            String s = userData[5];
            if(!s.equals("\"\"")) {
                String[] ed = userData[5].split("-");
                String[] et = userData[6].split(":");
                endDate = LocalDateTime.of(Integer.parseInt(ed[0]),
                        Integer.parseInt(ed[1]),
                        Integer.parseInt(ed[2]),
                        Integer.parseInt(et[0]),
                        Integer.parseInt(et[1]));
            }
        }
        catch (DateTimeException | NumberFormatException e) {
            System.out.println("Error in endDate data, example: 2000-10-15.");
            return WorkerData;
        }

        try {
            String pos;
            pos = userData[7];

            if (!pos.equals("\"\""))
                position = Position.valueOf(pos);
        }
        catch (IllegalArgumentException e){
            System.out.println("Incorrect position data. Example: MANAGER.");
            return WorkerData;
        }

        person = createNewPerson(userData[8],userData[9],userData[10],userData[11],userData[12], person);

        if(person != null) {
            worker = new Worker(id,
                    name,
                    coordinates,
                    ZonedDateTime.now(),
                    salary,
                    startDate,
                    endDate,
                    position,
                    person);

            WorkerData.add(worker);
        }
        return WorkerData;
    }

    @Override
    public String getName () {
            return "add";
        }

    @Override
    public String getHelp () {
        return info;
    }


        public Person createNewPerson(String birthdayDate, String birthdayTime, String height, String weight, String passportID, Person person){

                try {
                    if (birthdayDate.equals("\"\""))
                        person.setBirthday(null);
                    else {
                        String[] perDate = birthdayDate.split("-");
                        String[] perTime = birthdayTime.split(":");

                        person.setBirthday(LocalDateTime.of(Integer.parseInt(perDate[0]),
                                Integer.parseInt(perDate[1]),
                                Integer.parseInt(perDate[2]),
                                Integer.parseInt(perTime[0]),
                                Integer.parseInt(perTime[1])));
                    }

                    } catch(DateTimeException | NumberFormatException e){
                        System.out.println("Invalid birthday field data. Example: 2000-10-12 16:35.");
                }


                try {
                    person.setHeight(Integer.parseInt(height));
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect data type: height.");
                }

                try {
                    if(weight.equals("\"\""))
                        person.setWeight(null);
                    else
                        person.setWeight(Float.parseFloat(weight));
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect data type: weight.");
                }

                try {
                    if(passportID.length()<30 && passportID.length() > 3)
                        person.setPassportID(passportID);
                    else
                        throw new IOException();
                } catch (IOException e) {
                    System.out.println("Incorrect length: passportID(3<x<30).");
                }

            return person;
        }

    }

