import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Command command;
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

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

public void run() {

        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                String userInput = sc.nextLine();
                Parser parsed = new Parser(userInput);
                command = new Command(parsed);
                command.execute(ui, taskList, storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }



}
