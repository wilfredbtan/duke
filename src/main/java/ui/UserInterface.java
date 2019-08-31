package ui;

import exception.DukeException;
import task.Task;
import tasklist.TaskListInterface;

public interface UserInterface {
    public void showWelcome();
    public void showBye();
    public void showList(TaskListInterface taskList) throws DukeException;
    public void showError(String errorMessage);
    public void showLoadingError();
    public void showInvalidInputError();
    public void showDoneSuccess(Task doneTask);
    public void showAddSuccess(Task currEvent, TaskListInterface taskList);
    public void showDeleteSuccess(Task deletedTask, TaskListInterface taskList);
}
