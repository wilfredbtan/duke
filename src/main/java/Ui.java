public class Ui {
    public void showWelcome() {
        String welcomeString = "";
        welcomeString +=
                ("    Hello! I'm Duke\n") +
                ("    What can I do for you?\n");
        wrapWithLine(welcomeString);
    }

    public void showBye() {
        wrapWithLine("    Bye. Hope to see you again soon!\n");
    }

    public void showList() {
        String listString = "";
        for (int i = 0; i < TaskList.tasks.size(); i++) {
            listString += "    " + (i+1) + ". " + TaskList.tasks.get(i) + "\n";
        }
        wrapWithLine(listString);
    }

    public void showLoadingError() {
        wrapWithLine("    Initialised with empty TaskList\n");
    }

    public void showGeneralError() {
        wrapWithLine("    \u2639  OOPS!! I'm sorry, but I don't know what that means :-(\n");
    }

    public void showTodoError() {
        wrapWithLine("    ☹  OOPS!!! The description of a todo cannot be empty.\n");
    }

    public void showDateError() {
        wrapWithLine("    invalid date format.\n");
    }

    public static void showAddSuccess(Task addedTask) {
        String successString = "";
        successString +=
                ("    Got it. I've added this task:\n") +
                ("      " + addedTask + "\n") +
                ("    Now you have " + TaskList.tasks.size() + " tasks in this list.\n");
        wrapWithLine(successString);
    }

    public static void showAddFailure(Task failedTask) {
        wrapWithLine("    Sorry, that's an incomplete command\n");
    }

    public void showDeleteSuccess(Task deletedTask) {
        String deleteString = "";
        deleteString +=
                ("    Noted. I've removed this task:\n") +
                ("      " + deletedTask + "\n") +
                ("    Now you have " + TaskList.tasks.size() + " tasks in this list.\n");
        wrapWithLine(deleteString);
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
