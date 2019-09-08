package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Message;

import java.time.LocalDate;
import java.time.LocalTime;

public class DeadlineCommand extends Command {

    /** Description portion of the input. */
    private String desc;
    private LocalDate date;
    private LocalTime time;

    public DeadlineCommand(String desc, LocalDate date, LocalTime time) {
        this.desc = desc;
        this.date = date;
        this.time = time;
    }

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        Task deadline = new Deadline(desc, date, time);
        taskList.add(deadline);
        return new CommandResult(Message.addSuccess(deadline, taskList));
    }
}
