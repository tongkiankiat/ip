import java.util.Scanner;

public class Sean {
    // Create class Task to store a task
    public static class Task {
        protected String description;
        protected boolean isDone;

        // Constructor
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        // Methods
        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsUndone() {
            this.isDone = false;
        }
    }

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[100];
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
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println(i + 1 + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
                }
            }
            // Unmarking a task
            else if (input.startsWith("unmark") && input.split(" ")[1].matches("\\d+")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                if (taskIndex > taskCounter) {
                    System.out.println("Task number out of range!");
                    continue;
                }
                if (!taskList[taskIndex - 1].isDone) {
                    System.out.println("Task is already unmarked.");
                    continue;
                }
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  [ ] " + taskList[taskIndex - 1].description);
                taskList[taskIndex - 1].markAsUndone();
            }
            // Marking a task
            else if (input.startsWith("mark") && input.split(" ")[1].matches("\\d+")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                if (taskIndex > taskCounter) {
                    System.out.println("Task number out of range!");
                    continue;
                }
                if (taskList[taskIndex - 1].isDone) {
                    System.out.println("Task is already marked as done.");
                    continue;
                }
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [X] " + taskList[taskIndex - 1].description);
                taskList[taskIndex - 1].markAsDone();
            }
            // Else we store the task in the list
            else {
                System.out.println("added: " + input);
                taskList[taskCounter] = new Task(input);
                taskCounter++;
            }
        }
    }
}
