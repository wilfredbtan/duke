import command.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import storage.StorageInterface;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class used to drive the program.
 * @author Wilfred Bradley Tan, A0185405E.
 */

public class Duke {

    /** Object used to execute commands. */
    private Command command;
    /** User interface to display feedback and instructions to user. */
    private Ui ui;
    /** Storage object to facilitate loading and saving of tasks. */
    private StorageInterface storage;
    /** List of tasks added by the user. */
    private TaskList taskList;

    /**
     * Creates Duke with an absolute filePath.
     * @param args String[]
     */
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

    /** Initialises a Duke object with a filePath and loads existing tasks if any.
     * @param filePath Filepath where tasks will be saved and loaded from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        ui.showWelcome();
        storage = new Storage(filePath);

        try {
            taskList = storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /** Initiates the system by requesting for user input. Executes the next command after parsed
     * by the Parser and fed to the command object. Error messages will be shown if invalid commands are given.
     */
    public void run() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                String userInput = sc.nextLine();
                Parser parsed = new Parser(userInput);
                command = new Command(parsed);

                ui.showLine();
                command.execute(ui, taskList, storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }



}
