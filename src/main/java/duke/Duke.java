package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.StorageInterface;
import duke.tasklist.TaskList;
import duke.ui.Message;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * duke.Main class used to drive the program.
 * @author Wilfred Bradley Tan, A0185405E.
 */
public class Duke {

    /** Object used to execute commands. */
    private Command command;
    /** Storage object to facilitate loading and saving of tasks. */
    private StorageInterface storage = new Storage();
    /** List of tasks added by the user. */
    private TaskList taskList;

    private final Logger logger = Logger.getLogger(Duke.class.getName());

    /** Initiates the system by requesting for user input. Executes the next command after parsed //     * by the Parser and fed to the command object. Error messages will be shown if invalid commands are given. //     */
    public void run() {
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            logger.warning(Message.LOADING_ERROR);
            taskList = new TaskList();
        }
    }

    public CommandResult getResponse(String userInput) {
        try {
            logger.info("---------- Initialising Duke, your best friend! ----------");
            command = Parser.parse(userInput);
            return command.execute(taskList);
        } catch (DukeException e) {
            return new CommandResult(Message.error(e.getMessage()));
        }
    }
}
