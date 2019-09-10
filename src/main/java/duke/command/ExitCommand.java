package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Message;

public class ExitCommand extends Command{

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        return new CommandResult(Message.EXIT, true);
    }
}
