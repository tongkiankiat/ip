import java.util.Scanner;

public class Sean {
    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Sean");
        System.out.println("What can I do for you?");

        // Echoes user input
        while (true) {
            input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                in.close();
                return;
            }
            System.out.println(input);
        }
    }
}
