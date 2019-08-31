package task;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String description, LocalDate startDate, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.type = "E";
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String toString() {
        return "[E]" + super.toString() + "(at: " + startDate + " " + startTime
                + "-" + endTime + ")";
    }
}
