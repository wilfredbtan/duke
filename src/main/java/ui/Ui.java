package ui;

import exception.DukeException;
import task.Task;
import tasklist.TaskListInterface;

/**
 * Ui class which generates feedback to the user.
 */
public class Ui implements UserInterface {

    /**
     * Shows a welcome message.
     */
    public void showWelcome() {
        String welcomeString =
                ("    Hello! I'm Duke\n") +
                        ("    What can I do for you?\n");
        wrapWithLine(welcomeString);
    }

    /**
     * Shows a good bye message.
     */
    public void showBye() {
        wrapWithLine("    Bye. Hope to see you again soon!\n");
    }

    /**
     * Shows the list of tasks.
     * @param taskList TaskList that is to be shown.
     * @throws DukeException Throws an exception if it is an invalid list or there exists and invalid task in the list.
     */
    public void showList(TaskListInterface taskList) throws DukeException {
        String listString = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            listString += "    " + (i + 1) + ". " + taskList.get(i) + "\n";
        }
        wrapWithLine(listString);
    }

    /**
     * Shows a formatted error message.
     * @param errorMessage Error message that will be printed.
     */
    public void showError(String errorMessage) {
        wrapWithLine(errorMessage);
    }

    /**
     * Shows the loading error message.
     */
    public void showLoadingError() {
        wrapWithLine("    No existing data. Duke initialised with an empty TaskList\n");
    }

    /**
     * Shows the invalid input error message.
     */
    public void showInvalidInputError() {
        wrapWithLine("    \u2639  OOPS!! I'm sorry, but I don't know what that means :-(\n");
    }

    /**
     * Shows a message when a task has been successfully added.
     * @param addedTask Task that is to be added.
     * @param taskList TaskList that the Task is to be added to.
     */
    public void showAddSuccess(Task addedTask, TaskListInterface taskList) {
        String successString =
                ("    Got it. I've added this task:\n") +
                ("      " + addedTask + "\n") +
                ("    Now you have " + taskList.getSize() + " tasks in this list.\n");
        wrapWithLine(successString);
    }

    /**
     * Shows a message when a task has been successfully deleted.
     * @param deletedTask Task that is to be deleted.
     * @param taskList TaskList that the Task is to be deleted from.
     */
    public void showDeleteSuccess(Task deletedTask, TaskListInterface taskList) {
        String deleteString =
                ("    Noted. I've removed this task:\n") +
                ("      " + deletedTask + "\n") +
                ("    Now you have " + taskList.getSize() + " tasks in this list.\n");
        wrapWithLine(deleteString);
    }

    /**
     * Shows a message when a task is successfully marked as done.
     * @param doneTask Task that is to be marked as done.
     */
    public void showDoneSuccess(Task doneTask) {
        String doneString =
                ("     Nice! I've marked this task as done:\n") +
                ("     " + doneTask + "\n");
        wrapWithLine(doneString);
    }

    /**
     * Shows a standard line string.
     */
    public static void showLine() {
        System.out.println("   ____________________________________________________________");
    }

    /**
     * Wraps strings with surrounding lines to standardise system output.
     * @param input Input string that is to be formatted.
     */
    public static void wrapWithLine(String input) {
        showLine();
        System.out.print(input);
        showLine();
    }

}
