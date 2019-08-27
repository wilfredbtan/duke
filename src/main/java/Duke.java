import java.util.Arrays;
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
                    System.out.println("todo:" + todo);
                    Task currTodo = new Todo(todo);
                    taskList.add(currTodo);

                    System.out.println("   ____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("    " + currTodo);
                    System.out.println("    Now you have " + taskList.size() + " tasks in this list.");
                    System.out.println("   ____________________________________________________________");
                    break;

                case "deadline":
                    String deadline = sc.nextLine();
                    String[] descriptionAndDate = deadline.split("/");

                    Task currDeadline = new Deadline(descriptionAndDate[0], descriptionAndDate[1]);
                    taskList.add(currDeadline);

                    System.out.println("   ____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("    " + currDeadline);
                    System.out.println("    Now you have " + taskList.size() + " tasks in this list.");
                    System.out.println("   ____________________________________________________________");
                    break;

                case "event":
                    String event = sc.nextLine();
                    descriptionAndDate = event.split("/");

                    Task currEvent = new Event(descriptionAndDate[0], descriptionAndDate[1]);
                    taskList.add(currEvent);

                    System.out.println("   ____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("    " + currEvent);
                    System.out.println("    Now you have " + taskList.size() + " tasks in this list.");
                    System.out.println("   ____________________________________________________________");
                    break;

                case "done":
                    int index = sc.nextInt();
                    Task currTask = taskList.get(index - 1);
                    currTask.setDone();

                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("     " + currTask);
                    System.out.println("    ____________________________________________________________");
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
                    System.out.println("    not a valid instruction");
                    System.out.println("   ____________________________________________________________");
                    break;
            }
        }
    }

}
