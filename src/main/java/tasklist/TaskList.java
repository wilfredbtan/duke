package tasklist;

import exception.DukeException;
import storage.StorageInterface;
import task.Task;

import java.util.ArrayList;

/**
 * TaskList class which handles the commands related to the task list such as add and remove.
 */
public class TaskList implements TaskListInterface {
    /** List of tasks*/
    private ArrayList<Task> tasks;

    /**
     * Initialises the TaskList object with an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialises the TaskList object with the provided task list.
     * @param taskList Task list used to initialise TaskList object.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Adds a task to the task list.
     * @param task Task that is to be added to the list.
     * @param storage Storage object to save changes.
     * @throws DukeException Exception is thrown when an invalid task is added. For example, an incomplete command or
     * tasks with null description.
     */
    public void add(Task task, StorageInterface storage) throws DukeException {
        if (task.getDescription() != null) {
            tasks.add(task);
            storage.save(this);
        } else {
            throw new DukeException("    Sorry, that's an incomplete command. Failed to add task.", null);
        }
    }

    /**
     * Removes the task from the task list.
     * @param index Index of the task to be removed.
     * @param storage Storage object to save changes.
     * @throws DukeException Exception is thrown when an invalid index is provided i.e. IndexOutOfBoundsException
     */
    public void remove(int index, StorageInterface storage) throws DukeException{
        tasks.remove(index);
        storage.save(this);
    }

    /**
     * Sets the task as done in the Storage.
     * @param task Task which will be set as done.
     * @param storage Storage object to save changes.
     * @throws DukeException Exception is thrown when an invalid index is provided i.e. IndexOutOfBoundsException
     */
    public void setDone(Task task, StorageInterface storage) throws DukeException{
        task.setDone(true);
        storage.save(this);
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
    public Task get(int index) {
        return tasks.get(index);
    }

}
