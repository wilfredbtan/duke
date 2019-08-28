import java.io.*;
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
    private File file;

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

        file = new File("/Users/wilfredbradleytan/Documents/Uni_Material/2" +
                ".1/CS2103/indiv_proj/duke/src/main/java/data/duke.txt");

        try {
            loadData();
        } catch (IOException e) { }


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

                try {
                    appendData(currTodo);
                } catch (IOException e ){}

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

                    appendData(currDeadline);

                } catch (IOException e ){
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
                    try {
                        appendData(currEvent);
                    } catch (IOException e ){}

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

                    rewriteData(taskList);

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

                    rewriteData(taskList);

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

    private String formatData(Task currTask) {
        String data = "";
        data += currTask.type + "|";
        data += currTask.isDone ? "1|" : "0|";
        data += currTask.description;


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm", Locale.ENGLISH);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm", Locale.ENGLISH);

        if (currTask instanceof Deadline) {
            data += "|" + ((Deadline) currTask).by.format(dateTimeFormatter);
        }
        if (currTask instanceof Event) {
            data +=  "|" + ((Event) currTask).startDate.format(dateTimeFormatter)
                    + "|" + ((Event) currTask).endTime.format(timeFormatter);
        }
        return data;

    }

    private void appendData(Task currTask) throws IOException {
        String formattedData = formatData(currTask);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

        writer.append(formattedData);
        writer.append("\n");
        writer.close();
    }

    private void rewriteData(ArrayList<Task> taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Task task : taskList) {
                appendData(task);
            }
        } catch (IOException e) { }
    }

    private void loadData() throws IOException {
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);

        //load tasks into task list.
        String line;
        while ((line = br.readLine()) != null) {
            Task newTask = convertStringToTask(line);
            taskList.add(newTask);
        }
    }

    private Task convertStringToTask(String taskData) {
        boolean isDone;
        Task newTask;

        String[] dataArr = taskData.split("[|]");
        String type = dataArr[0];

        if (dataArr[1].equals("1")) {
            isDone = true;
        } else {
            isDone = false;
        }

        switch (type) {
        case "T":
            newTask = new Todo(dataArr[2]);
            newTask.isDone = isDone;
            break;
        case "D":
            LocalDateTime date = null;

            try {
                date = parseDate(dataArr[3]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            newTask = new Deadline(dataArr[2], date);
            newTask.isDone = isDone;
            break;
        case "E":
            String desc = dataArr[2];

            LocalDateTime startDate = null;
            LocalTime endTime = null;
            try {
                startDate = parseDate(dataArr[3]);
                endTime = LocalTime.parse(dataArr[4], DateTimeFormatter.ofPattern("HHmm"));
            } catch (ParseException e){ }

            newTask = new Event(desc, startDate, endTime);
            newTask.isDone = isDone;
            break;
        default:
            System.out.println("not a valid task");
            newTask = null;
            break;
        }

        return newTask;
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
