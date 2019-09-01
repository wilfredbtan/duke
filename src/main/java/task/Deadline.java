package task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deadline class indicated with type "D" which contains a date and time on top of other Task components.
 */
public class Deadline extends Task {

    /** Date of the Deadline. */
    private LocalDate date;
    /** Time of the Deadline. */
    private LocalTime time;

    /**
     * Initialises a Deadline object.
     * @param description Description of the Deadline.
     * @param date Date of the Deadline.
     * @param time Time of the Deadline.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.type = "D";
        this.date = date;
        this.time = time;
    }

    /**
     * String of the type followed by status icon, description, date and time of Deadline.
     * @return String in the given format.
     */
    public String toString() {
        return "[D]" + super.toString() + "(by: " + date + " " + time + ")";
    }

    /**
     * Gets the date of the Deadline.
     * @return Date of the Deadline.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Gets the time of the Deadline.
     * @return Time of the Deadline.
     */
    public LocalTime getTime() {
        return this.time;
    }
}
