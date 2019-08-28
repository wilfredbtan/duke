import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task{
    protected LocalDateTime startDate;
    protected LocalTime endTime;

    public Event(String description, LocalDateTime startDate, LocalTime endTime) {
        super(description);
        this.startDate = startDate;
        this.endTime = endTime;
    }

    public String toString() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"
                , Locale.ENGLISH);
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm", Locale.ENGLISH);
        return "[E]" + super.toString() + "(at: " + startDate.format(dateTimeFormat)
                + "-" + endTime.format(timeFormat) + ")";
    }
}
