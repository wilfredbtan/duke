public class Task {

    protected String type;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }

}
