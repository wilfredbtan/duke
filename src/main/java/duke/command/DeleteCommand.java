package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Message;

public class DeleteCommand extends Command {

    private int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        Task deletedTask = taskList.get(deleteIndex);
        taskList.delete(deleteIndex);
        return new CommandResult(Message.showDeleteSuccess(deletedTask, taskList));
    }
}
