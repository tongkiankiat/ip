import java.util.Scanner;
import java.util.Arrays;

public class Sean {
    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int taskCounter = 0;

        System.out.println("Hello! I'm Sean");
        System.out.println("What can I do for you?");

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
                    System.out.println(i + 1 + "." + taskList[i].toString());
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
                taskList[taskIndex - 1].markAsUndone();
                System.out.println("  " + taskList[taskIndex - 1].toString());
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
                taskList[taskIndex - 1].markAsDone();
                System.out.println("  " + taskList[taskIndex - 1].toString());

            }
            // Else we move on to todo, deadline, or task prompts
            else {
                if (input.startsWith("todo")) {
                    String todoTask = input.split("todo")[1].strip();
                    taskList[taskCounter] = new Todo(todoTask, todoTask);
                    taskCounter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  [T][ ] " + todoTask);
                    System.out.println("Now you have " + taskCounter + (taskCounter == 1 ? " task " : " tasks ") + "in the list.");
                }
                else if (input.startsWith("deadline")) {
                    String deadlineTask = input.split("deadline | /by")[1].strip();
                    String deadlineBy = input.split("/by")[1].strip();
                    taskList[taskCounter] = new Deadline(deadlineTask, deadlineBy);
                    taskCounter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  [D][ ] " + deadlineTask + " (by: " + deadlineBy + ")");
                    System.out.println("Now you have " + taskCounter + (taskCounter == 1 ? " task " : " tasks ") + "in the list.");
                }
                else if (input.startsWith("event")) {
                    String eventTask = input.split("event | /from | /to")[1].strip();
                    String eventFrom = input.split("/from | /to")[1].strip();
                    String eventTo = input.split("/to")[1].strip();
                    taskList[taskCounter] = new Event(eventTask, eventFrom, eventTo);
                    taskCounter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  [E][ ] " + eventTask + " (from: " + eventFrom + " to: " + eventTo + ")");
                    System.out.println("Now you have " + taskCounter + " tasks in the list.");
                }
                else {
                    System.out.println("added: " + input);
                    taskList[taskCounter] = new Task(input);
                    taskCounter++;
                }
            }
        }
    }
}
