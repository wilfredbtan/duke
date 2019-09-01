package command;

import exception.DukeException;
import parser.Parser;
import storage.StorageInterface;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import tasklist.TaskList;
import ui.Ui;

/**
 * Command class that executes parsed commands given to it.
 */
public class Command {

    /** Parsed object which contains variables that can be accessed by a command object.*/
    Parser parsed;

    /**
     * Initialises a Command object.
     * @param parsed Parsed object which contains variables that can be accessed by a command object.
     */
    public Command(Parser parsed) {
        this.parsed = parsed;
    }

    /**
     * Drives the system by processing the given commands and doing the corresponding actions.
     * @param ui UserInterface which provides feedback to the user.
     * @param taskList TaskList which carries out tasks which edit the task list.
     * @param storage Storage which determines the location and format of saved data.
     * @throws DukeException Exception is thrown when invalid or incomplete commands are given.
     */
    public void execute(Ui ui, TaskList taskList, StorageInterface storage) throws DukeException {
        switch (parsed.getCommandString()) {
        case "find":
            TaskList filteredList = taskList.find(parsed.getKeyword());
            ui.showFindSuccess();
            ui.showList(filteredList);
            break;

        case "todo":
            Task currTodo = new Todo(parsed.getDesc());
            taskList.add(currTodo, storage);
            ui.showAddSuccess(currTodo, taskList);
            break;

        case "deadline":
            Task currDeadline = new Deadline(parsed.getDesc(), parsed.getStartDate(), parsed.getStartTime());
            taskList.add(currDeadline, storage);
            ui.showAddSuccess(currDeadline, taskList);
            break;

        case "event":
            Task currEvent = new Event(parsed.getDesc(), parsed.getStartDate(), parsed.getStartTime(),
                    parsed.getEndTime());
            taskList.add(currEvent, storage);
            ui.showAddSuccess(currEvent, taskList);
            break;

        case "delete":
            try {
                Task deletedTask = taskList.get(parsed.getIndex() - 1);
                taskList.remove(parsed.getIndex() - 1, storage);
                ui.showDeleteSuccess(deletedTask, taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("     Deleted item is out of bounds! Task not deleted", e);
            }
            break;

        case "done":
            try {
                Task doneTask = taskList.get(parsed.getIndex() - 1);
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

}
