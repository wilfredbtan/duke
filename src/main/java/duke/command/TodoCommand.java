package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Message;

public class TodoCommand extends Command {

    /** Description portion of the input. */
    private String desc;

    public TodoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        Task todo = new Todo(desc);
        taskList.add(todo);
        return new CommandResult(Message.showAddSuccess(todo, taskList));
    }
}
