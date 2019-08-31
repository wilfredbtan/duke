package ui;

import exception.DukeException;
import task.Task;
import tasklist.TaskListInterface;

public class Ui implements UserInterface {
    public void showWelcome() {
        String welcomeString =
                ("    Hello! I'm Duke\n") +
                        ("    What can I do for you?\n");
        wrapWithLine(welcomeString);
    }

    public void showBye() {
        wrapWithLine("    Bye. Hope to see you again soon!\n");
    }

    public void showList(TaskListInterface taskList) throws DukeException {
        String listString = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            listString += "    " + (i + 1) + ". " + taskList.get(i) + "\n";
        }
        wrapWithLine(listString);
    }

    public void showError(String errorMessage) {
        wrapWithLine(errorMessage);
    }

    public void showLoadingError() {
        wrapWithLine("    No existing data. Duke initialised with an empty TaskList\n");
    }

    public void showInvalidInputError() {
        wrapWithLine("    \u2639  OOPS!! I'm sorry, but I don't know what that means :-(\n");
    }

    public void showAddSuccess(Task addedTask, TaskListInterface taskList) {
        String successString =
                ("    Got it. I've added this task:\n") +
                ("      " + addedTask + "\n") +
                ("    Now you have " + taskList.getSize() + " tasks in this list.\n");
        wrapWithLine(successString);
    }

    public void showDeleteSuccess(Task deletedTask, TaskListInterface taskList) {
        String deleteString =
                ("    Noted. I've removed this task:\n") +
                ("      " + deletedTask + "\n") +
                ("    Now you have " + taskList.getSize() + " tasks in this list.\n");
        wrapWithLine(deleteString);
    }

    public void showDoneSuccess(Task doneTask) {
        String doneString =
                ("     Nice! I've marked this task as done:\n") +
                ("     " + doneTask + "\n");
        wrapWithLine(doneString);
    }

    public static void showLine() {
        System.out.println("   ____________________________________________________________");
    }

    public static void wrapWithLine(String input) {
        showLine();
        System.out.print(input);
        showLine();
    }

}
