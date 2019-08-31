package tasklist;

import exception.DukeException;
import storage.StorageInterface;
import task.Task;

import java.util.ArrayList;

public interface TaskListInterface {
    public void add(Task task, StorageInterface storage) throws DukeException;

    public void remove(int index, StorageInterface storage) throws DukeException;

    public void setDone(Task task, StorageInterface storage) throws DukeException;

    public ArrayList<Task> getTasks();

    public int getSize();

    public Task get(int index) throws DukeException;
}
