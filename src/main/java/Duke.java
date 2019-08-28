import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
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
                        String[] descriptionAndDate = deadline.split("/", 2);

                        String desc = descriptionAndDate[0];
                        LocalDateTime date;

                        try {
                            date = parseDate(descriptionAndDate[1]);
                        } catch (ParseException e) {
                            System.out.println("Invalid date format");
                            date = null;
                        }

                        Task currDeadline = new Deadline(desc, date);
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
                        String[] descriptionAndDate = event.split("/", 2);

                        String desc = descriptionAndDate[0];
                        String[] dateArr = descriptionAndDate[1].split("-");
                        LocalDateTime startDate = parseDate(dateArr[0]);
                        LocalTime endTime = LocalTime.parse(dateArr[1], DateTimeFormatter.ofPattern("HHmm"));

                        Task currEvent = new Event(desc, startDate, endTime);
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
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Invalid date format");
                    } catch (ParseException e) {
                        System.out.println("Invalid date format");
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format");
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

    private LocalDateTime parseDate(String dateString) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm", Locale.ENGLISH);
        LocalDateTime date = LocalDateTime.parse(dateString, formatter);
        return date;
    }

    private LocalDateTime[] parseDateRange(String dateString) throws  ParseException {
        String[] dateArr = dateString.split("-");
        LocalDateTime startDate = parseDate(dateArr[0]);
        System.out.println(dateArr[1]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm", Locale.ENGLISH);
        LocalDateTime endTime = LocalDateTime.parse(dateArr[1], formatter);

        LocalDateTime[] dateRange = {startDate, endTime};

        return dateRange;
    }

}
