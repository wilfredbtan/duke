package ui;

import task.Task;
import tasklist.TaskList;

public interface UserInterface {
    public void showWelcome();
    public void showBye();
    public void showList(TaskList taskList);
    public void showError(String errorMessage);
    public void showLoadingError();
    public void showInvalidInputError();
    public void showDoneSuccess(Task doneTask);
    public void showAddSuccess(Task currEvent, TaskList taskList);
    public void showDeleteSuccess(Task deletedTask, TaskList taskList);
}
