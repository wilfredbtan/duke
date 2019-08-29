public class Command {

    Parser parsed;
    Storage storage;

    public Command(Parser parsed, Storage storage) {
        this.parsed = parsed;
        this.storage = storage;
    }

    //main driver
    public void execute() {
        Ui ui = new Ui();
        switch (parsed.getCommandString()) {
        case "todo":
            Task currTodo = new Todo(parsed.getDesc());
            TaskList.add(currTodo, storage);
            break;

        case "deadline":
            Task currDeadline = new Deadline(parsed.getDesc(), parsed.getStartDate(), parsed.getStartTime());
            TaskList.add(currDeadline, storage);
            break;

        case "event":
            Task currEvent = new Event(parsed.getDesc(), parsed.getStartDate(), parsed.getStartTime(),
                    parsed.getEndTime());
            TaskList.add(currEvent, storage);
            break;

        case "delete":
            try {
                Task deletedTask = TaskList.tasks.get(parsed.getIndex() - 1);
                TaskList.tasks.remove(parsed.getIndex() - 1);
                ui.showDeleteSuccess(deletedTask);
//            } catch (InputMismatchException e) {
//                System.out.println("   ____________________________________________________________");
//                System.out.println("    Deletion should refer to a number!");
//                System.out.println("   ____________________________________________________________");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("   ____________________________________________________________");
                System.out.println("     Deleted item is out of bounds!");
                System.out.println("   ____________________________________________________________");
            }
            break;

        case "done":
            Task currTask = TaskList.tasks.get(parsed.getIndex() - 1);
            currTask.setDone(true);

            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("     " + currTask);
            System.out.println("    ____________________________________________________________");

//            } catch (IndexOutOfBoundsException e) {
//                System.out.println("   ____________________________________________________________");
//                System.out.println("     Selected item is out of bounds!");
//                System.out.println("   ____________________________________________________________");
//            }
            break;
        case "list":
            ui.showList();
            break;
        case "bye":
            ui.showBye();
            System.exit(1);
            break;
        default:
            ui.showGeneralError();
            break;
        }
    }

}
