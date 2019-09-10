package duke.ui;

import duke.Duke;
import duke.Main;

import duke.command.CommandResult;
import duke.exception.DukeException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final String FXML = "/view/MainWindow.fxml";
    private final FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private Stage primaryStage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private final Logger logger = Logger.getLogger(MainWindow.class.getName());

    public MainWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;

        fxmlLoader.setLocation(getFxmlFileUrl(FXML));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(primaryStage);

        duke = new Duke();
        duke.run();

        try {
            fxmlLoader.load();
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(Message.WELCOME, dukeImage)
            );

        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void show() {
        primaryStage.show();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        CommandResult response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.getUserFeedback(), dukeImage)
        );
        userInput.clear();

        if (response.isExit()) {
            handleExit();
        }
    }

    private void handleExit() {
        logger.info("---------Bye! See you again----------");
        System.exit(1);
    }

    private static URL getFxmlFileUrl(String fxmlFileName) {
        requireNonNull(fxmlFileName);
        URL fxmlFileUrl = Main.class.getResource(fxmlFileName);
        return requireNonNull(fxmlFileUrl);
    }

}