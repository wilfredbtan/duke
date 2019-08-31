package task;

/**
 * Task class which can be extended to create sub tasks.
 */
public class Task {

    /** Type of Task. */
    protected String type;
    /** Description of Task. */
    protected String description;
    /** Boolean indicated whether Task is done or not. */
    protected boolean isDone;

    /**
     * Initialises a Task object.
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task. Shows a tick if done and an "x" if Task is not done.
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Sets the Task as done or not depending on the argument.
     * @param isDone boolean to determine whether Task is marked as done or not.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the type of the Task.
     * @return Type of the task.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the description of the Task.
     * @return Description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the status of the Task (i.e. whether done or not).
     * @return Status of the Task.
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * String of the status icon followed by description.
     * @return String in the given format.
     */
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }

}
