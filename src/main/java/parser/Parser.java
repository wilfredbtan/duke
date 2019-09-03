package parser;

import command.Command;
import exception.DukeException;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Parser class used to process Strings into variables that the Command object can execute.
 */
public class Parser {

    /** Command portion of the input. */
    private String commandString;
    /** Description portion of the input. */
    private String desc;
    /** Date when the task starts. */
    private LocalDate startDate;
    /** Time when the task starts. */
    private LocalTime startTime;
    /** Time when the task ends. */
    private LocalTime endTime;
    /** Index for deletion or marking tasks as done. */
    private int index;
    /** Keyword for finding the task in a list. */
    private String keyword;


    /**
     * Parses user input into a Command object containing its required components such as description, date and time
     *      according to the command given.
     * @param userInput Input provided by the user.
     * @throws DukeException Exception is thrown when there is an invalid format provided. For example,
     *      missing date / time or incorrect date / time format.
     */
    public static Command parse(String userInput) throws DukeException {
        Scanner sc = new Scanner(userInput);
        Command newCommand = new Command();
        String commandString = sc.next();
        newCommand.addCommandString(commandString);

        switch (commandString) {
        case "find":
            newCommand.addKeyword(sc.next());
            break;
        case "todo":
            newCommand.addDesc(sc.next());
            break;
        case "deadline":
            //Fallthrough
        case "event":
            try {
                String[] descriptionAndDate = sc.nextLine().split("/", 2);
                newCommand.addDesc(descriptionAndDate[0]);

                String[] dateTimeArr = descriptionAndDate[1].split(" ");

                newCommand.addStartDate(LocalDate.parse(dateTimeArr[0], dateFormatter()));
                String[] timeRange = dateTimeArr[1].split("-");
                if (timeRange.length <= 1) {
                    newCommand.addStartTime(LocalTime.parse(dateTimeArr[1], timeFormatter()));
                } else {
                    newCommand.addStartTime(LocalTime.parse(timeRange[0], timeFormatter()));
                    newCommand.addEndTime(LocalTime.parse(timeRange[1], timeFormatter()));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("    Incomplete command, please include a date and time", e);
            } catch (NoSuchElementException e) {
                throw new DukeException("    Incomplete command, please add a description, date and time.", e);
            }
            break;
        case "delete":
            //Fallthrough
        case "done":
            newCommand.addIndex(sc.nextInt());
            break;
        case "list":
            //Fallthrough
        case "bye":
            break;
        default:
            throw new DukeException("     ☹ OOPS!! I'm sorry, but I don't know what that means :-(", null);
        }

        return newCommand;
    }

    /**
     * Parses Strings into Task objects.
     * @param taskData String in the format type|isDone|desc|startDate(optional)|startTime(optional)|endTime(optional).
     * @return Task object.
     * @throws ParseException Exception is thrown when an invalid task is loaded.
     */
    public static Task parseStringToTask(String taskData) throws ParseException {
        Task newTask;
        String type;
        boolean isDone;
        String desc;
        LocalDate newStartDate;
        LocalTime newStartTime;
        LocalTime newEndTime;

        String[] dataArr = taskData.split("[|]");

        type = dataArr[0];
        isDone = (dataArr[1].equals("1")) ? true : false;
        desc = dataArr[2];

        switch (type) {
        case "T":
            newTask = new Todo(desc);
            newTask.setDone(isDone);
            break;
        case "D":
            newStartDate = LocalDate.parse((dataArr[3]), dateFormatter());
            newStartTime = LocalTime.parse((dataArr[4]), timeFormatter());

            newTask = new Deadline(desc, newStartDate, newStartTime);
            newTask.setDone(isDone);
            break;
        case "E":
            newStartDate = LocalDate.parse((dataArr[3]), dateFormatter());
            newStartTime = LocalTime.parse((dataArr[4]), timeFormatter());
            newEndTime = LocalTime.parse((dataArr[5]), timeFormatter());

            newTask = new Event(desc, newStartDate, newStartTime, newEndTime);
            newTask.setDone(isDone);
            break;
        default:
            throw new ParseException("invalid task loaded", 0);
        }

        return newTask;
    }

    /**
     * DateTimeFormatter which can be used to validate the formats of date strings.
     * @return DateTimeFormatter to validate date formats.
     */
    public static DateTimeFormatter dateFormatter() {
        return DateTimeFormatter.ofPattern("[dd/MM/yyyy][dd/MM/yy][dd-MM-yyyy]"
                + "[dd-MM-yy]", Locale.ENGLISH);
    }

    /**
     * DateTimeFormatter which can be used to validate the formats of time strings.
     * @return DateTimeFormatter to validate time formats.
     */
    public static DateTimeFormatter timeFormatter() {
        return DateTimeFormatter.ofPattern("[HHmm][HH:mm][H]", Locale.ENGLISH);
    }

}
