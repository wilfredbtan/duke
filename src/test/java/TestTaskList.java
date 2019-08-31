import exception.DukeException;
import storage.StorageInterface;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import tasklist.TaskList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestTaskList {

    private StorageInterface storageStub = new StorageStub();

    public ArrayList<Task> createList(Task task) {
        ArrayList<Task> newList = new ArrayList<>();
        newList.add(task);
        return newList;
    }

    @Test
    public void add_tasks_success() throws DukeException {
        Task todoStub = new Todo(" testString");
        Task deadlineStub = new Deadline(" testString ", dateStub(), timeStub());
        Task eventStub = new Event(" testString ", dateStub(), timeStub(), timeStub());

        ArrayList<Task> testTodoList = createList(todoStub);
        TaskList todoTaskList = new TaskList();
        todoTaskList.add(todoStub, storageStub);
        assertEquals(testTodoList, todoTaskList.getTasks());

        ArrayList<Task> testDeadlineList = createList(deadlineStub);
        TaskList deadlineTaskList = new TaskList();
        deadlineTaskList.add(deadlineStub, storageStub);
        assertEquals(testDeadlineList, deadlineTaskList.getTasks());

        ArrayList<Task> testEventList = createList(eventStub);
        TaskList eventTaskList = new TaskList();
        eventTaskList.add(eventStub, storageStub);
        assertEquals(testEventList, eventTaskList.getTasks());
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
