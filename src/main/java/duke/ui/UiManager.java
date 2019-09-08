package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;
import javafx.stage.Stage;

/**
 * Ui class which generates feedback to the user.
 */
public class UiManager {

    private MainWindow mainWindow;

    public void start(Stage primaryStage) {
        try {
            mainWindow = new MainWindow(primaryStage);
            mainWindow.show(); //This should be called before creating other UI parts
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
