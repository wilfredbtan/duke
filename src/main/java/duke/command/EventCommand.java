package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Message;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventCommand extends Command {

    /** Description portion of the input. */
    private String desc;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public EventCommand(String desc, LocalDate startDate, LocalTime startTime, LocalTime endTime) {
        this.desc = desc;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        Task event = new Event(desc, startDate, startTime, endTime);
        taskList.add(event);
        return new CommandResult(Message.showAddSuccess(event, taskList));
    }
}
