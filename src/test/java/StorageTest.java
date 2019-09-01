import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    private final String FILEPATH = "tasks.txt";

    @Test
    public void formatData_todo_success() {
        Task todoStub = new Todo(" testString");
        assertEquals("T|0| testString", new Storage(FILEPATH).formatData(todoStub));
    }

    @Test
    public void formatData_deadline_success() {
        Task deadlineStub = new Deadline(" testString ", dateStub(), timeStub());
        assertEquals("D|0| testString |26/11/1996|1234", new Storage(FILEPATH).formatData(deadlineStub));
    }

    @Test
    public void formatData_event_success() {
        Task eventStub = new Event(" testString ", dateStub(), timeStub(), timeStub());
        assertEquals("E|0| testString |26/11/1996|1234|1234", new Storage(FILEPATH).formatData(eventStub));
    }

    public static LocalDate dateStub() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        return LocalDate.parse("26/11/1996", formatter);
    }

    public static LocalTime timeStub() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm", Locale.ENGLISH);
        return LocalTime.parse("1234", formatter);
    }

}
