import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Command command;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public Duke(String filePath) {
        ui = new Ui();
        ui.showWelcome();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

public void run() {

        while (true) {
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();
            Parser parsed = new Parser(userInput);
            command = new Command(parsed, storage);
            command.execute();
        }
    }



}
