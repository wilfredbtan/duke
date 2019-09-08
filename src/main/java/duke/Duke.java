package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.StorageInterface;
import duke.tasklist.TaskList;
import duke.ui.Message;
import duke.ui.UiManager;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * duke.Main class used to drive the program.
 * @author Wilfred Bradley Tan, A0185405E.
 */
public class Duke {

    /** Object used to execute commands. */
    private Command command;
    /** User interface to display feedback and instructions to user. */
    private UiManager ui = new UiManager();
    /** Storage object to facilitate loading and saving of tasks. */
    private StorageInterface storage = new Storage();
    /** List of tasks added by the user. */
    private TaskList taskList;

    private final Logger logger = Logger.getLogger(Duke.class.getName());

    /** Initiates the system by requesting for user input. Executes the next command after parsed //     * by the Parser and fed to the command object. Error messages will be shown if invalid commands are given. //     */
    public void run() {
        try {
            taskList = new TaskList(storage.load());
            for (duke.task.Task t : taskList.getTasks()) {
                logger.info("loaded tasks" + t.toString());
            }
        } catch (IOException e) {
            logger.info(Message.loadingError());
            taskList = new TaskList();
        }
    }

    public String getResponse(String userInput) {
        String output;
        logger.info("reached");
        try {
            for (duke.task.Task t : taskList.getTasks()) {
                logger.info("responded things " + t.toString());
            }
            command = Parser.parse(userInput);
            output = command.execute(taskList).getUserFeedback();
        } catch (DukeException e) {
            output = Message.error(e.getMessage());
        }
        return output;
    }
}
