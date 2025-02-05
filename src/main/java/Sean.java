import java.util.Scanner;
import java.util.Arrays;

public class Sean {
    // Initialise Array to store Tasks
    public static Task[] taskList = new Task[100];
    public static int taskCounter = 0;

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Sean");
        System.out.println("What can I do for you?");

        while (true) {
            input = in.nextLine();
            // End conversation when user says bye
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            // Display list when asked
            if (input.equals("list")) {
                displayTaskList();
            }
            // Unmarking a task
            else if (input.startsWith("unmark") && input.split(" ")[1].matches("\\d+")) {
                markTask(input, false);
            }
            // Marking a task
            else if (input.startsWith("mark") && input.split(" ")[1].matches("\\d+")) {
                markTask(input, true);
            }
            // Else we move on to todo, deadline, or task prompts
            else {
                if (input.startsWith("todo")) {
                    addTodoTask(input);
                }
                else if (input.startsWith("deadline")) {
                    addDeadlineTask(input);
                }
                else if (input.startsWith("event")) {
                    addEventTask(input);
                }
                else {
                    addNormalTask(input);
                }
            }
        }
        in.close();
    }

    // Display task list
    public static void displayTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; i++) {
            System.out.println(i + 1 + "." + taskList[i].toString());
        }
    }

    // Marking/Unmarking Task
    public static void markTask(String input, boolean isDone) {
        int taskIndex = Integer.parseInt(input.split(" ")[1]);

        if (taskIndex < 0 || taskIndex > taskCounter) {
            System.out.println("Task number out of range!");
            return;
        }
        if (taskList[taskIndex - 1].isDone == isDone) {
            System.out.println((isDone ? "Task is already marked." : "Task is already unmarked"));
            return;
        }

        System.out.println(isDone ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:");

        if (isDone) {
            taskList[taskIndex - 1].markAsDone();
        } else {
            taskList[taskIndex - 1].markAsUndone();
        }

        System.out.println("  " + taskList[taskIndex - 1].toString());
    }

    // Add todo task
    public static void addTodoTask(String input) {
        String todoTask = input.split("todo")[1].strip();
        taskList[taskCounter] = new Todo(todoTask, todoTask);
        taskCounter++;
        printTaskAdded("  [T][ ] " + todoTask);
    }

    // Add deadline task
    public static void addDeadlineTask(String input) {
        String deadlineTask = input.split("deadline | /by")[1].strip();
        String deadlineBy = input.split("/by")[1].strip();
        taskList[taskCounter] = new Deadline(deadlineTask, deadlineBy);
        taskCounter++;
        printTaskAdded("  [D][ ] " + deadlineTask + " (by: " + deadlineBy + ")");
    }

    // Add event task
    public static void addEventTask(String input) {
        String eventTask = input.split("event | /from | /to")[1].strip();
        String eventFrom = input.split("/from | /to")[1].strip();
        String eventTo = input.split("/to")[1].strip();
        taskList[taskCounter] = new Event(eventTask, eventFrom, eventTo);
        taskCounter++;
        printTaskAdded("  [E][ ] " + eventTask + " (from: " + eventFrom + " to: " + eventTo + ")");
    }

    // Add normal task
    public static void addNormalTask(String input) {
        taskList[taskCounter] = new Task(input);
        taskCounter++;
        System.out.println("added: " + input);
    }

    // Print task added
    public static void printTaskAdded(String uniqueMessage) {
        System.out.println("Got it. I've added this task:");
        System.out.println(uniqueMessage);
        System.out.println("Now you have " + taskCounter + (taskCounter == 1 ? " task " : " tasks ") + "in the list.");
    }
}
