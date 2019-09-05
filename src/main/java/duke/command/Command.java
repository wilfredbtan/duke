package duke.command;

import duke.exception.DukeException;
import duke.storage.StorageInterface;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.UiManager;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Command class that executes parsed commands given to it.
 */
public class Command {

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
     * Drives the system by processing the given commands and doing the corresponding actions.
     * @param ui UserInterface which provides feedback to the user.
     * @param taskList TaskList which carries out tasks which edit the task list.
     * @param storage Storage which determines the location and format of saved data.
     * @throws DukeException Exception is thrown when invalid or incomplete commands are given.
     */
    public void execute(UiManager ui, TaskList taskList, StorageInterface storage) throws DukeException {
        switch (getCommandString()) {
        case "find":
            TaskList filteredList = taskList.find(getKeyword());
            ui.showFindSuccess();
            ui.showList(filteredList);
            break;

        case "todo":
            Task currTodo = new Todo(getDesc());
            taskList.add(currTodo, storage);
            ui.showAddSuccess(currTodo, taskList);
            break;

        case "deadline":
            Task currDeadline = new Deadline(getDesc(), getStartDate(), getStartTime());
            taskList.add(currDeadline, storage);
            ui.showAddSuccess(currDeadline, taskList);
            break;

        case "event":
            Task currEvent = new Event(getDesc(), getStartDate(), getStartTime(),
                    getEndTime());
            taskList.add(currEvent, storage);
            ui.showAddSuccess(currEvent, taskList);
            break;

        case "delete":
            try {
                Task deletedTask = taskList.get(getIndex() - 1);
                taskList.remove(getIndex() - 1, storage);
                ui.showDeleteSuccess(deletedTask, taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("     Deleted item is out of bounds! Task not deleted", e);
            }
            break;

        case "done":
            try {
                Task doneTask = taskList.get(getIndex() - 1);
                taskList.setDone(doneTask, storage);
                ui.showDoneSuccess(doneTask);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("     Selected item is out of bounds! Task not deleted", e);
            }
            break;
        case "list":
            ui.showList(taskList);
            break;
        case "bye":
            ui.showBye();
            System.exit(1);
            break;
        default:
            ui.showInvalidInputError();
        }
    }

    /**
     * Adds a command to the current Command object and generates a new Command for method chaining.
     * @return Command object with command added.
     */
    public Command addCommandString(String commandString) {
        this.commandString = commandString;
        return this;
    }

    /**
     * Adds a description to the current Command object and generates a new Command for method chaining.
     * @return Command object with description added.
     */
    public Command addDesc(String desc) {
        this.desc = desc;
        return this;
    }

    /**
     * Adds a start date to the current Command object and generates a new Command for method chaining.
     * @return Command object with start date added.
     */
    public Command addStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Adds a start time to the current Command object and generates a new Command for method chaining.
     * @return Command object with start time added.
     */
    public Command addStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Adds an end time to the current Command object and generates a new Command for method chaining.
     * @return Command object with end time added.
     */
    public Command addEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Adds an index to the current Command object and generates a new Command for method chaining.
     * @return Command object with index added.
     */
    public Command addIndex(int index) {
        this.index = index;
        return this;
    }

    /**
     * Adds a keyword to the current Command object and generates a new Command for method chaining.
     * @return Command object with keyword added.
     */
    public Command addKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    /**
     * Gets the command of the Command object.
     * @return Command of Task.
     */
    public String getCommandString() {
        return this.commandString;
    }

    /**
     * Gets the description of the Command object.
     * @return Description of Task.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Gets the start date of the Command object.
     * @return Start Date of Task.
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Gets the start time of the Command object.
     * @return Start time of Task.
     */
    public LocalTime getStartTime() {
        return this.startTime;
    }

    /**
     * Gets the end time of the Command object.
     * @return End time of the Task.
     */
    public LocalTime getEndTime() {
        return this.endTime;
    }

    /**
     * Gets the index of the Command object.
     * @return Index of Task.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Gets the keyword of the Command object.
     * @return Keyword of the Command object.
     */
    public String getKeyword() {
        return this.keyword;
    }

}
