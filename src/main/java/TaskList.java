import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public static void add(Task task, Storage storage) {
        try {
            tasks.add(task);
            storage.save();
            Ui.showAddSuccess(task);
        } catch (NullPointerException e) {
            Ui.showAddFailure(task);
        }
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

}
