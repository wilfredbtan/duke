package duke.tasklist;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.StorageInterface;
import duke.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * TaskList class which handles the commands related to the task list such as add and remove.
 */
public class TaskList {
    /** List of tasks. */
    private ArrayList<Task> tasks;
    private StorageInterface storage;

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
            tasks.add(task);
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
            tasks.remove(index);
            storage.save(this);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("That index is out of range! Task not deleted.", e);
        }
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

}
