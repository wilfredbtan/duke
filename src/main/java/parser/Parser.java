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

public class Parser implements Parsable {

    private String userInput;
    private String commandString;
    private String desc;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int index;
    private String keyword;

    public Parser (String userInput) throws DukeException {
        this.userInput = userInput;
        parse();
    }

    private void parse() throws DukeException {
        Ui ui = new Ui();

        Scanner sc = new Scanner(userInput);

        this.commandString = sc.next();

        if (commandString.equals("list") || commandString.equals("bye")) {
            return;
        }

        if (commandString.equals("find")) {
            this.keyword = sc.nextLine();
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

    private void parseDateTime(String dateString) throws ParseException {
        String[] dateTimeArr = dateString.split(" ");

        this.startDate = LocalDate.parse(dateTimeArr[0], dateFormatter());
        String[] timeRange = dateTimeArr[1].split("-");
        if (timeRange.length <= 1) {
            this.startTime = LocalTime.parse(dateTimeArr[1], timeFormatter());
        } else {
            this.startTime = LocalTime.parse(timeRange[0], timeFormatter());
            this.endTime = LocalTime.parse(timeRange[1], timeFormatter());
        }
    }

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

    public static DateTimeFormatter dateFormatter() {
        return DateTimeFormatter.ofPattern("[dd/MM/yyyy][dd/MM/yy][dd-MM-YYYY]" +
                "[dd-MM-YY]", Locale.ENGLISH);
    }

    public static DateTimeFormatter timeFormatter() {
        return DateTimeFormatter.ofPattern("[HHmm][HH:mm][H]", Locale.ENGLISH);
    }

    public String getDesc() {
        return this.desc;
    }

    public String getCommandString() {
        return this.commandString;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public int getIndex() {
        return this.index;
    }

    public String getKeyword() {
        return this.keyword;
    }
}
