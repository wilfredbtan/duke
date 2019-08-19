import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while(true) {
            String word = sc.next();
            switch (word) {
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println("  Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    System.exit(1);
                    break;
                case "list":
                    System.out.println("____________________________________________________________");

                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("  " + (i+1) + ". " + list.get(i));
                    }

                    System.out.println("____________________________________________________________");
                    break;
                default:
                    System.out.println("____________________________________________________________");
                    System.out.println("  added: " + word);
                    System.out.println("____________________________________________________________");
                    list.add(word);
                    break;
            }
        }
    }
}
