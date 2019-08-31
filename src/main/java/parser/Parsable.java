package parser;

import java.time.LocalDate;
import java.time.LocalTime;

public interface Parsable {

    public String getDesc();

    public String getCommandString();

    public LocalDate getStartDate();

    public LocalTime getStartTime();

    public LocalTime getEndTime();

    public int getIndex();

}
