import java.io.*;
import java.util.InputMismatchException;
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
                    String[] descriptionAndDate = deadline.split("/");

                    Task currDeadline = new Deadline(descriptionAndDate[0], descriptionAndDate[1]);
                    taskList.add(currDeadline);

                    System.out.println("   ____________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + currDeadline);
                    System.out.println("    Now you have " + taskList.size() + " tasks in this list.");
                    System.out.println("   ____________________________________________________________");

                    try {
                        appendData(currDeadline);
                    } catch (IOException e ){}

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

                    try {
                        appendData(currEvent);
                    } catch (IOException e ){}

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
        if (currTask instanceof Deadline) {
            data += "|" + ((Deadline) currTask).by;
        }
        if (currTask instanceof Event) {
            data +=  "|" + ((Event) currTask).at;
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
            newTask = new Deadline(dataArr[2], dataArr[3]);
            newTask.isDone = isDone;
            break;
        case "E":
            newTask = new Event(dataArr[2], dataArr[3]);
            newTask.isDone = isDone;
            break;
        default:
            System.out.println("not a valid task");
            newTask = null;
            break;
        }

        return newTask;
    }

}
