//import parser.Parsable;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Locale;
//
//public class ParserStub implements Parsable {
//
//    private String commandString;
//
//    public ParserStub(String commandString) {
//        this.commandString = commandString;
//    }
//
//    public String getCommandString() {
//        return this.commandString;
//    }
//
//    public String getDesc() {
//        return "testString";
//    }
//
//    public LocalDate getStartDate() {
//        return LocalDate.parse("26/11/1996", dateFormatter());
//    }
//
//    public LocalTime getStartTime() {
//        return LocalTime.parse("1234", timeFormatter());
//    }
//
//    public LocalTime getEndTime() {
//        return LocalTime.parse("2345", timeFormatter());
//    }
//
//    public int getIndex() {
//        return 1;
//    }
//
//    public static DateTimeFormatter dateFormatter() {
//        return DateTimeFormatter.ofPattern("[dd/MM/yyyy][dd/MM/yy][dd-MM-YYYY]" +
//                "[dd-MM-YY]", Locale.ENGLISH);
//    }
//
//    public static DateTimeFormatter timeFormatter() {
//        return DateTimeFormatter.ofPattern("[HHmm][HH:mm][H]", Locale.ENGLISH);
//    }
//
//
//}
