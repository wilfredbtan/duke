package duke.tasklist;

import duke.Duke;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.StorageInterface;
import duke.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * TaskList class which handles the commands related to the task list such as add and remove.
 */
public class TaskList {
    /** List of tasks. */
    private ArrayList<Task> tasks;
    private StorageInterface storage;

    private static Logger logger = Logger.getLogger(TaskList.class.getName());

    /**
     * Initialises the TaskList object with an empty task list.
     */
    public TaskList() {
        this.storage = new Storage();
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.storage = new Storage();
        this.tasks = tasks;
    }

    public TaskList sort(String category) throws DukeException {
        ArrayList<Task> sortedTasks;

        switch (category) {
        case "description":
            sortedTasks = tasks.stream().sorted(Comparator.comparing(Task::getDescription))
                    .collect(Collectors.toCollection(ArrayList::new));
            break;
        case "type":
            sortedTasks = tasks.stream().sorted(Comparator.comparing(Task::getType))
                    .collect(Collectors.toCollection(ArrayList::new));
            break;
        case "done":
            sortedTasks = tasks.stream().sorted(Comparator.comparing(Task::getDone).reversed())
                    .collect(Collectors.toCollection(ArrayList::new));
            break;
        case "startDate":
            sortedTasks = tasks.stream().sorted(Comparator.comparing(Task::getStartDate))
                    .collect(Collectors.toCollection(ArrayList::new));
            break;
        case "startTime":
            sortedTasks = tasks.stream().sorted(Comparator.comparing(Task::getStartTime))
                    .collect(Collectors.toCollection(ArrayList::new));
            break;
        case "endTime":
            sortedTasks = tasks.stream().sorted(Comparator.comparing(Task::getEndTime))
                    .collect(Collectors.toCollection(ArrayList::new));
            break;
        default:
            throw new DukeException("Please sort using either description, type, done, startDate " +
                    "startTime or endTime.", null);
        }

        tasks = sortedTasks;
        storage.save(this);
        return this;
    }

    /**
     * Finds a task from the taskList.
     * @param keyword Keyword that user is searching for in task's description. For example, "test string" contains
     *                the "test" keyword.
     * @return TaskList that contains the tasks that were found.
     */
    public TaskList find(String keyword) {
        ArrayList<Task> filteredList =
                tasks.stream().filter(t -> t.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(filteredList);
    }

    /**
     * Adds a task to the task list.
     * @param task Task that is to be added to the list.
     * @throws DukeException Exception is thrown when an invalid task is added. For example, an incomplete command or
     *      tasks with null description.
     */
    public void add(Task task) throws DukeException {
        try {
            requireNonNull(task.getDescription());

            int sizeOfTasks = tasks.size();
            tasks.add(task);
            assert tasks.size() == sizeOfTasks++ : "Task was not added";

            storage.save(this);

        } catch (NullPointerException e) {
            throw new DukeException("Sorry, that's an incomplete command. Failed to add task.", null);
        }
    }

    /**
     * Removes the task from the task list.
     * @param index Index of the task to be removed.
     * @throws DukeException Exception is thrown when an invalid index is provided i.e. IndexOutOfBoundsException
     */
    public void delete(int index) throws DukeException {
        try {
            int sizeOfTasks = tasks.size();
            tasks.remove(index);
            assert tasks.size() == sizeOfTasks-- : "Task was not deleted";
            storage.save(this);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("That index is out of range! Task not deleted.", e);
        }
    }

    public void clear() {
        tasks.clear();
        storage.save(this);
    }

    /**
     * Sets the task as done in the Storage.
     * @param task Task which will be set as done.
     * @throws DukeException Exception is thrown when an invalid index is provided i.e. IndexOutOfBoundsException
     */
    public void setDone(Task task) throws DukeException {
        try {
            task.setDone(true);
            storage.save(this);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("That index is out of range! Task not marked as done.", e);
        }
    }

        /**
     * Gets the TaskList.
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the size of the task list.
     * @return Integer indicating the size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets the task from the index stated.
     * @param index Index of the task to be retrieved.
     * @return Task which is retrieved.
     */
    public Task get(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("That index is out of range! Task not marked as done.", e);
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

}
