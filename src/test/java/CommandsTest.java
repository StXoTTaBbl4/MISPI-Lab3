
import org.junit.Test;
import org.lab.Command.Commands.AddCommand;
import org.lab.DataClasses.Coordinates;
import org.lab.DataClasses.Person;
import org.lab.DataClasses.Position;
import org.lab.DataClasses.Worker;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;


public class CommandsTest {
    static LinkedList<Worker> workerData = new LinkedList<>();

//    @Test(timeout=5000)
    //@Test(expected=NullPointerException)
    @Test
    public void checkAddCommand(){
        AddCommand addCommand = new AddCommand();
        workerData = addCommand.handle("Kevin 10.5 10.5 15.5 2002-02-02 2020-12-12 15:50 MANAGER 2002-02-02 15:26 180 65 888888", workerData);
        Worker sample = new Worker();
        sample.setId(1);
        sample.setName("Kevin");
        sample.setCoordinates(new Coordinates(10.5f,10.5));
        sample.setCreationDate(ZonedDateTime.now());
        sample.setSalary(15.5f);
        sample.setStartDate(LocalDate.of(2002, 2,2));
        sample.setEndDate(LocalDateTime.of(2020,12,12,15,50));
        sample.setPosition(Position.MANAGER);
        sample.setPerson(new Person(
                LocalDateTime.of(2002,2,2,15,26),
                180,
                65.0f,
                888888+""));

        assertEquals(sample.getName(),workerData.get(0).getName());
        assertEquals(sample.getCoordinates().getX(),workerData.get(0).getCoordinates().getX());
        assertEquals(sample.getCoordinates().getY(),workerData.get(0).getCoordinates().getY());
        assertEquals(sample.getSalary(),workerData.get(0).getSalary());
        assertEquals(sample.getEndDate(),workerData.get(0).getEndDate());
        assertEquals(sample.getPosition(),workerData.get(0).getPosition());
    }

}
