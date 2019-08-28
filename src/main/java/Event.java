public class Event extends Task{
    protected String at;

    public Event(String description, String by) {
        super(description);
        this.at = by;
        this.type = "E";
    }

    public String toString() {
        return "[" + this.type + "]" + super.toString() + "(at: " + at + ")";
    }
}
