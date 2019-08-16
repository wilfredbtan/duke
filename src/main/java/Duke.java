import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        while(true) {
            String word = sc.next();
            if (word.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("  Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                sc.close();
                break;
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("  " + word);
                System.out.println("____________________________________________________________");
            }
        }
    }
}
