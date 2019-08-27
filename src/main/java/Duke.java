import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("   ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("   ____________________________________________________________");

        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while(true) {
            String word = sc.next();
            switch (word) {
                case "todo":
                    String todo = sc.nextLine();
                    if (todo.equals("")) {
                        System.out.println("   ____________________________________________________________");
                        System.out.println("    ☹  OOPS!!! The description of a todo cannot be empty.");
                        System.out.println("   ____________________________________________________________");
                        break;
                    }
                    System.out.println("todo:" + todo);
                    Task currTodo = new Todo(todo);
                    taskList.add(currTodo);

                    System.out.println("   ____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + currTodo);
                    System.out.println("    Now you have " + taskList.size() + " tasks in this list.");
                    System.out.println("   ____________________________________________________________");
                    break;

                case "deadline":
                    String deadline = sc.nextLine();
                    try {
                        String[] descriptionAndDate = deadline.split("/");

                        Task currDeadline = new Deadline(descriptionAndDate[0], descriptionAndDate[1]);
                        taskList.add(currDeadline);

                        System.out.println("   ____________________________________________________________");
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      " + currDeadline);
                        System.out.println("    Now you have " + taskList.size() + " tasks in this list.");
                        System.out.println("   ____________________________________________________________");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("   ____________________________________________________________");
                        System.out.println("    Deadlines must have a date!");
                        System.out.println("   ____________________________________________________________");
                    }
                    break;

                case "event":
                    String event = sc.nextLine();
                    try {
                        String[] descriptionAndDate = event.split("/");

                        Task currEvent = new Event(descriptionAndDate[0], descriptionAndDate[1]);
                        taskList.add(currEvent);

                        System.out.println("   ____________________________________________________________");
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      " + currEvent);
                        System.out.println("    Now you have " + taskList.size() + " tasks in this list.");
                        System.out.println("   ____________________________________________________________");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("   ____________________________________________________________");
                        System.out.println("    Events must have a date!");
                        System.out.println("   ____________________________________________________________");
                    }
                    break;

                case "delete":
                    try {
                        int deletionIndex = sc.nextInt() - 1;
                        Task deletedTask = taskList.get(deletionIndex);

                        taskList.remove(deletionIndex);

                        System.out.println("   ____________________________________________________________");
                        System.out.println("    Noted. I've removed this task:");
                        System.out.println("      " + deletedTask);
                        System.out.println("    Now you have " + taskList.size() + " tasks in this list.");
                        System.out.println("   ____________________________________________________________");
                    } catch (InputMismatchException e) {
                        System.out.println("   ____________________________________________________________");
                        System.out.println("    Deletion should refer to a number!");
                        System.out.println("   ____________________________________________________________");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("   ____________________________________________________________");
                        System.out.println("     Deleted item is out of bounds!");
                        System.out.println("   ____________________________________________________________");
                    }
                    break;

                case "done":
                    try {
                        int index = sc.nextInt();
                        Task currTask = taskList.get(index - 1);
                        currTask.setDone();

                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println("     " + currTask);
                        System.out.println("    ____________________________________________________________");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("   ____________________________________________________________");
                        System.out.println("     Selected item is out of bounds!");
                        System.out.println("   ____________________________________________________________");
                    }
                    break;
                case "list":
                    System.out.println("   ____________________________________________________________");

                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("    " + (i+1) + ". " + taskList.get(i));
                    }

                    System.out.println("   ____________________________________________________________");
                    break;
                case "bye":
                    System.out.println("   ____________________________________________________________");
                    System.out.println("    Bye. Hope to see you again soon!");
                    System.out.println("   ____________________________________________________________");
                    System.exit(1);
                    break;
                default:
                    System.out.println("   ____________________________________________________________");
                    System.out.println("    \u2639  OOPS!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("   ____________________________________________________________");
                    break;
            }
        }
    }

}
