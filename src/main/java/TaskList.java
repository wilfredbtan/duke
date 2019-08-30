import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public void add(Task task, Storage storage) throws DukeException{
        if (task.getDescription() != null) {
            tasks.add(task);
            storage.save(this);
            Ui.showAddSuccess(task, this);
        } else {
            System.out.println("   ____________________________________________________________");
            System.out.println("    Sorry, that's an incomplete command. Failed to add task.");
            System.out.println("   ____________________________________________________________");
        }
    }

    public void remove(int index, Storage storage) {
        tasks.remove(index);
        storage.save(this);
    }

    public void setDone(Task task, Storage storage) {
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
