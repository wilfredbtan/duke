public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.type = "D";
        this.by = by;
    }

    public String toString() {
        return "[" + this.type + "]" + super.toString() + "(by: " + by + ")";
    }

}
