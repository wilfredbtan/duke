package duke.ui;

import duke.DialogBox;
import duke.Duke;
import duke.Main;
import duke.ui.UiManager;

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

    public MainWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;

        fxmlLoader.setLocation(getFxmlFileUrl(FXML));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(primaryStage);

        duke = new Duke();

        try {
            fxmlLoader.load();
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog("    Hello! I'm Duke\n"
                            + "    What can I do for you?", dukeImage)
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
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    private static URL getFxmlFileUrl(String fxmlFileName) {
        requireNonNull(fxmlFileName);
        URL fxmlFileUrl = Main.class.getResource(fxmlFileName);
        return requireNonNull(fxmlFileUrl);
    }

    public void setDuke(Duke d) {
        duke = d;
    }

}