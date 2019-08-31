//import command.Command;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
//
//public class CommandTest {
//
//    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
//    private final PrintStream originalOut = System.out;
//    private final PrintStream originalErr = System.err;
//
//    @Before
//    public void setUpStreams() {
//        System.setOut(new PrintStream(outContent));
//        System.setErr(new PrintStream(errContent));
//    }
//
//    @After
//    public void restoreStreams() {
//        System.setOut(originalOut);
//        System.setErr(originalErr);
//    }
//
//    @Test
//    public void execute_todo_success() {
//        String todoSuccess =
//        "    ____________________________________________________________\n" +
//        "      Got it. I've added this task:\n" +
//        "        [T][?] testString\n" +
//        "      Now you have 1 tasks in this list.\n" +
//        "    ____________________________________________________________";
//
//        ParserStub parserStub = new ParserStub("todo");
//        Command testCommand = new Command(parserStub);
////        testCommand.execute(uiStub, taskListStub, storageStub);
//
//        assertEquals(todoSuccess, outContent.toString());
//    }
//
//}
