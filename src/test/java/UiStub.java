//import task.Task;
//import tasklist.TaskList;
//import ui.UserInterface;
//
//public class UiStub implements UserInterface {
//
//    public void showDeleteSuccess(Task deletedTask, TaskList taskList) {
//        String deleteString =
//                ("    Noted. I've removed this task:\n") +
//                        ("      " + deletedTask + "\n") +
//                        ("    Now you have " + taskList.getSize() + " tasks in this list.\n");
//        wrapWithLine(deleteString);
//    }
//
//    public void showDoneSuccess(Task doneTask) {
//        String doneString =
//                ("     Nice! I've marked this task as done:\n") +
//                        ("     " + doneTask + "\n");
//        wrapWithLine(doneString);
//    }
//
//    public void showList(TaskList taskList) {
//        String listString = "";
//        for (int i = 0; i < taskList.getSize(); i++) {
//            listString += "    " + (i + 1) + ". " + taskList.get(i) + "\n";
//        }
//        wrapWithLine(listString);
//    }
//
//    public void showBye() {
//        wrapWithLine("    Bye. Hope to see you again soon!\n");
//    }
//
//    public void showInvalidInputError() {
//        wrapWithLine("    \u2639  OOPS!! I'm sorry, but I don't know what that means :-(\n");
//    }
//
//    public static void showLine() {
//        System.out.println("   ____________________________________________________________");
//    }
//
//    public static void wrapWithLine(String input) {
//        showLine();
//        System.out.print(input);
//        showLine();
//    }
//}
