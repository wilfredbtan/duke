package duke.command;

public class CommandResult {

    private String userFeedback;
    private boolean isExit;

    public CommandResult(String userFeedback, boolean isExit) {
        this.userFeedback = userFeedback;
        this.isExit = isExit;
    }

    public CommandResult(String userFeedback) {
        this.userFeedback = userFeedback;
        this.isExit = false;
    }

    public String getUserFeedback() {
        return this.userFeedback;
    }

    public boolean isExit() {
        return this.isExit;
    }
}
