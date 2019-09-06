package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.StorageInterface;
import duke.tasklist.TaskList;
import duke.ui.UiManager;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * duke.Main class used to drive the program.
 * @author Wilfred Bradley Tan, A0185405E.
 */
public class Duke {

    /** Object used to execute commands. */
    private Command command = new Command();
    /** User interface to display feedback and instructions to user. */
    private UiManager ui = new UiManager();
    /** Storage object to facilitate loading and saving of tasks. */
    private StorageInterface storage = new Storage("tasks.txt");
    /** List of tasks added by the user. */
    private TaskList taskList = new TaskList();

    private final Logger logger = Logger.getLogger(Duke.class.getName());

    /**
     * Creates Duke with an absolute filePath.
     * @param args String[]
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /** Initiates the system by requesting for user input. Executes the next command after parsed
     * by the Parser and fed to the command object. Error messages will be shown if invalid commands are given.
     */
    public void run() {
        try {
            taskList = storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String userInput) {
        String output;
        try {
            command = Parser.parse(userInput);
            output = command.execute(ui, taskList, storage);
        } catch (DukeException e) {
            logger.info("reached");
            output = ui.showError(e.getMessage());
        }
        return output;
    }
}
