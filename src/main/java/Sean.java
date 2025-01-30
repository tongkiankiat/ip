import java.util.Scanner;
import java.util.Arrays;

public class Sean {
    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        String[] taskList = new String[100];
        int taskCounter = 0;

        System.out.println("Hello! I'm Sean");
        System.out.println("What can I do for you?");

        // Echoes user input
        while (true) {
            input = in.nextLine();
            // End conversation when user says bye
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                in.close();
                return;
            }

            // Display list when asked
            if (input.equals("list")) {
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println(Integer.toString(i + 1) + ". " + taskList[i]);
                }
            }
            // Else we store the task in the list
            else {
                System.out.println("added: " + input);
                taskList[taskCounter] = input;
                taskCounter++;
            }
        }
    }
}
