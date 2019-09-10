package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;

public class Message {
    public static String WELCOME = "Hello! I'm Duke\n" + "What can I do for you?";
    public static String EXIT = "Bye. Hope to see you again soon!";
    public static String EMPTY_LIST = "No items on this list!";
    public static String LOADING_ERROR = "No existing data. Duke initialised with an empty TaskList";
    public static String GENERAL_INVALID_INPUT = "\u2639 OOPS!! I'm sorry, but I don't know what that means :-(";

    /**
     * Shows the list of tasks.
     * @param taskList TaskList that is to be shown.
     * @throws DukeException Throws an exception if it is an invalid list or there exists and invalid task in the list.
     */
    public static String showList(TaskList taskList) throws DukeException {
        String list = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            list += (i + 1) + ". " + taskList.get(i) + "\n";
        }

        return list;
    }

    /**
     * Shows a formatted error message.
     * @param errorMessage Error message that will be printed.
     */
    public static String error(String errorMessage) {
        return errorMessage;
    }

    /**
     * Shows a message of the list of tasks found.
     * @param taskList Filtered tasklist that will be displayed.
     * @return List of tasks found.
     * @throws DukeException
     */
    public static String findSuccess(TaskList taskList) throws DukeException {
        return "Here are the matching tasks in your list:\n"
                + showList(taskList);
    }

    /**
     * Shows a message when a task has been successfully added.
     * @param addedTask Task that is to be added.
     * @param taskList TaskList that the Task is to be added to.
     */
    public static String addSuccess(Task addedTask, TaskList taskList) {
        return "Got it. I've added this task:\n"
                + addedTask + "\n"
                + "Now you have " + taskList.getSize() + " tasks in this list.";
    }

    /**
     * Shows a message when a task has been successfully deleted.
     * @param deletedTask Task that is to be deleted.
     * @param taskList TaskList that the Task is to be deleted from.
     */
    public static String deleteSuccess(Task deletedTask, TaskList taskList) {
        return "Noted. I've removed this task:\n"
                + deletedTask + "\n"
                + "Now you have " + taskList.getSize() + " tasks in this list.";
    }

    /**
     * Shows a message when a task is successfully marked as done.
     * @param doneTask Task that is to be marked as done.
     */
    public static String doneSuccess(Task doneTask) {
        return "Nice! I've marked this task as done:\n"
                + doneTask;
    }

}
