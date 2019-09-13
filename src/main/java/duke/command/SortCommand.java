package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Message;

public class SortCommand extends Command {

    private String category;

    public SortCommand(String category) {
        this.category = category;
    }

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        TaskList sortedList = taskList.sort(category);
        return new CommandResult(Message.showSortSuccess(sortedList));
    }

}
