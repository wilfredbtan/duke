package ui;

import exception.DukeException;
import task.Task;
import tasklist.TaskList;

/**
 * Ui class which generates feedback to the user.
 */
public class Ui {

    /**
     * Shows a welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("    Hello! I'm Duke\n"
                + "    What can I do for you?");
        showLine();
    }

    /**
     * Shows a good bye message.
     */
    public void showBye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    /**
     * Shows the list of tasks.
     * @param taskList TaskList that is to be shown.
     * @throws DukeException Throws an exception if it is an invalid list or there exists and invalid task in the list.
     */
    public void showList(TaskList taskList) throws DukeException {
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println("    " + (i + 1) + ". " + taskList.get(i));
        }
    }

    /**
     * Shows a formatted error message.
     * @param errorMessage Error message that will be printed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Shows the loading error message.
     */
    public void showLoadingError() {
        System.out.println("    No existing data. Duke initialised with an empty TaskList");
    }

    /**
     * Shows the invalid input error message.
     */
    public void showInvalidInputError() {
        System.out.println("     ☹ OOPS!! I'm sorry, but I don't know what that means :-(");
    }

    public void showFindSuccess() {
        System.out.println("     Here are the matching tasks in your list:");
    }

    /**
     * Shows a message when a task has been successfully added.
     * @param addedTask Task that is to be added.
     * @param taskList TaskList that the Task is to be added to.
     */
    public void showAddSuccess(Task addedTask, TaskList taskList) {
        System.out.println(
                "    Got it. I've added this task:\n"
                + "      " + addedTask + "\n"
                + "    Now you have " + taskList.getSize() + " tasks in this list.");
    }

    /**
     * Shows a message when a task has been successfully deleted.
     * @param deletedTask Task that is to be deleted.
     * @param taskList TaskList that the Task is to be deleted from.
     */
    public void showDeleteSuccess(Task deletedTask, TaskList taskList) {
        System.out.println(
                "    Noted. I've removed this task:\n"
                + "      " + deletedTask + "\n"
                + "    Now you have " + taskList.getSize() + " tasks in this list.");
    }

    /**
     * Shows a message when a task is successfully marked as done.
     * @param doneTask Task that is to be marked as done.
     */
    public void showDoneSuccess(Task doneTask) {
        System.out.println(
                "     Nice! I've marked this task as done:\n"
                + "     " + doneTask);
    }

    /**
     * Shows a standard line string.
     */
    public void showLine() {
        System.out.println("   ____________________________________________________________");
    }
}
