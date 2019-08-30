public class Command {

    Parser parsed;

    public Command(Parser parsed) {
        this.parsed = parsed;
    }

    //main driver
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException{
        ui = new Ui();
        switch (parsed.getCommandString()) {
        case "todo":
            Task currTodo = new Todo(parsed.getDesc());
            taskList.add(currTodo, storage);
            break;

        case "deadline":
            try {
                Task currDeadline = new Deadline(parsed.getDesc(), parsed.getStartDate(), parsed.getStartTime());
                taskList.add(currDeadline, storage);
            } catch (NullPointerException e) {
                throw new DukeException("    Sorry, that's an incomplete command. Failed to add task.", e);
            }
            break;

        case "event":
            Task currEvent = new Event(parsed.getDesc(), parsed.getStartDate(), parsed.getStartTime(),
                    parsed.getEndTime());
            taskList.add(currEvent, storage);
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
                throw new DukeException("     Selected item is out of bounds!. Task not marked as done.", e);
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
