package command;

import exception.DukeException;
import parser.Parsable;
import storage.StorageInterface;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import tasklist.TaskList;
import ui.UserInterface;

public class Command {

    Parsable parsed;

    public Command(Parsable parsed) {
        this.parsed = parsed;
    }

    //main driver
    public void execute(UserInterface ui, TaskList taskList, StorageInterface storage) throws DukeException {
        switch (parsed.getCommandString()) {
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
