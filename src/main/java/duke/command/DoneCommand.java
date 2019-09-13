package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Message;

public class DoneCommand extends Command {

    private int doneIndex;

    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        Task doneTask = taskList.get(doneIndex);
        taskList.setDone(doneTask);
        return new CommandResult(Message.showDoneSuccess(doneTask));
    }
}
