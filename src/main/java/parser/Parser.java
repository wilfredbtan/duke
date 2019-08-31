package parser;

import exception.DukeException;
import ui.Ui;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

/**
 * Parser class used to process Strings into variables that the Command object can execute.
 */
public class Parser implements Parsable {

    /** Command portion of the input. */
    private String commandString;
    /** Description portion of the input. */
    private String desc;
    /** Date when the task starts. */
    private LocalDate startDate;
    /** Time whena the task starts. */
    private LocalTime startTime;
    /** Time when the task ends. */
    private LocalTime endTime;
    /** Index for deletion or marking tasks as done. */
    private int index;

    /**
     * Initialises the Parser object.
     * @param userInput Input provided by the user.
     * @throws DukeException
     */
    public Parser (String userInput) throws DukeException {
        parse(userInput);
    }

    /**
     * Parses user input into portions which can used to carry out commands.
     * @param userInput Input provided by the user.
     * @throws DukeException
     */
    private void parse(String userInput) throws DukeException {
        Ui ui = new Ui();

        Scanner sc = new Scanner(userInput);

        this.commandString = sc.next();

        if (commandString.equals("list") || commandString.equals("bye")) {
            return;
        }

        if (sc.hasNextInt()) {
            this.index = sc.nextInt();
        }

        if (sc.hasNextLine()) {
            String[] descriptionAndDate = sc.nextLine().split("/", 2);
            this.desc = descriptionAndDate[0];

            try {
                if (commandString.equals("deadline") || commandString.equals("event")) {
                    parseDateTime(descriptionAndDate[1]);
                }
            } catch (ParseException e) {
                throw new DukeException("    Parse exception Description", e);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("    Please include a date and time", e);
            }
        }
    }

    /**
     * Parses a DateTime string into formatted dates and times.
     * @param dateTimeString Unformatted String containing the date and time.
     * @throws ParseException
     */
    private void parseDateTime(String dateTimeString) throws ParseException {
        String[] dateTimeArr = dateTimeString.split(" ");

        this.startDate = LocalDate.parse(dateTimeArr[0], dateFormatter());
        String[] timeRange = dateTimeArr[1].split("-");
        if (timeRange.length <= 1) {
            this.startTime = LocalTime.parse(dateTimeArr[1], timeFormatter());
        } else {
            this.startTime = LocalTime.parse(timeRange[0], timeFormatter());
            this.endTime = LocalTime.parse(timeRange[1], timeFormatter());
        }
    }

    /**
     * Parses Strings into Task objects.
     * @param taskData String in the format type|isDone|desc|startDate(optional)|startTime(optional)|endTime(optional).
     * @return Task object.
     * @throws ParseException
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
        return DateTimeFormatter.ofPattern("[dd/MM/yyyy][dd/MM/yy][dd-MM-yyyy]" +
                "[dd-MM-yy]", Locale.ENGLISH);
    }

    /**
     * DateTimeFormatter which can be used to validate the formats of time strings.
     * @return DateTimeFormatter to validate time formats.
     */
    public static DateTimeFormatter timeFormatter() {
        return DateTimeFormatter.ofPattern("[HHmm][HH:mm][H]", Locale.ENGLISH);
    }

    /**
     * Gets the description of the parsed object.
     * @return Description of Task.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Gets the command of the parsed object.
     * @return Command of Task.
     */
    public String getCommandString() {
        return this.commandString;
    }

    /**
     * Gets the start date of the parsed object.
     * @return Start Date of Task.
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Gets the start time of the parsed object.
     * @return Start time of Task.
     */
    public LocalTime getStartTime() {
        return this.startTime;
    }

    /**
     * Gets the end time of the parsed object.
     * @return End time of the Task.
     */
    public LocalTime getEndTime() {
        return this.endTime;
    }

    /**
     * Gets the index of the parsed object.
     * @return Index of Task.
     */
    public int getIndex() {
        return this.index;
    }
}
