package tasklist;

import exception.DukeException;
import storage.StorageInterface;
import task.Task;

import java.util.ArrayList;

public class TaskList implements TaskListInterface {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public void add(Task task, StorageInterface storage) throws DukeException {
        if (task.getDescription() != null) {
            tasks.add(task);
            storage.save(this);
        } else {
            throw new DukeException("    Sorry, that's an incomplete command. Failed to add task.", null);
        }
    }

    public void remove(int index, StorageInterface storage) throws DukeException{
        tasks.remove(index);
        storage.save(this);
    }

    public void setDone(Task task, StorageInterface storage) throws DukeException{
        task.setDone(true);
        storage.save(this);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

}
