package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Message;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        TaskList filteredList = taskList.find(keyword);
        return new CommandResult(Message.showFindSuccess(filteredList));
    }
}
