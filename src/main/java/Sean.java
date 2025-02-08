import java.util.Scanner;

public class Sean {
    // Initialise Array to store Tasks
    public static int maxTaskCount = 100;
    public static Task[] taskList = new Task[maxTaskCount];
    public static int taskCounter = 0;

    public static void main(String[] args) throws SeanException {
        String input;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Sean");
        System.out.println("What can I do for you?");

        while (true) {
            input = in.nextLine().strip();
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
            else if (input.startsWith("unmark")) {
                markTask(input, false);
            }
            // Marking a task
            else if (input.startsWith("mark")) {
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
                    System.out.println("Unknown command, did you mistype a command?");
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
    public static void markTask(String input, boolean isDone) throws SeanException {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]);

            if (taskList[taskIndex - 1].isDone == isDone) {
                throw new SeanException((isDone ? "Task is already marked." : "Task is already unmarked"));
            }

            System.out.println(isDone ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:");

            if (isDone) {
                taskList[taskIndex - 1].markAsDone();
            } else {
                taskList[taskIndex - 1].markAsUndone();
            }

            System.out.println("  " + taskList[taskIndex - 1].toString());
        } catch (NumberFormatException e) {
            System.out.println("The task number is not valid! Are you sure you entered a number?");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Invalid task number! Please input a test number from 1 to " + maxTaskCount);
        }
    }

    // Add todo task
    public static void addTodoTask(String input) {
        try {
            String todoTask = input.split("todo")[1].strip();
            if (noDuplicateTasks(todoTask)) {
                taskList[taskCounter] = new Todo(todoTask, todoTask);
                taskCounter++;
                printTaskAdded("  [T][ ] " + todoTask);
            } else {
                printDuplicateTaskMessage();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please add a description for the todo task in the format: \"todo {task}\"");
        }
    }

    // Add deadline task
    public static void addDeadlineTask(String input) {
        try {
            String deadlineTask = input.split("deadline | /by")[1].strip();
            String deadlineBy = input.split("/by")[1].strip();
            if (noDuplicateTasks(deadlineTask)) {
                taskList[taskCounter] = new Deadline(deadlineTask, deadlineBy);
                taskCounter++;
                printTaskAdded("  [D][ ] " + deadlineTask + " (by: " + deadlineBy + ")");
            } else {
                printDuplicateTaskMessage();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please add a description and deadline for the deadline task in the format: \"deadline {task} /by {date/time}\"");
        }
    }

    // Add event task
    public static void addEventTask(String input) {
        try {
            String eventTask = input.split("event | /from | /to")[1].strip();
            String eventFrom = input.split("/from | /to")[1].strip();
            String eventTo = input.split("/to")[1].strip();
            if (noDuplicateTasks(eventTask)) {
                taskList[taskCounter] = new Event(eventTask, eventFrom, eventTo);
                taskCounter++;
                printTaskAdded("  [E][ ] " + eventTask + " (from: " + eventFrom + " to: " + eventTo + ")");
            } else {
                printDuplicateTaskMessage();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please add a description and a start and end date/time for the event task in the format: \"event {task} /from {date/time} /to {date/time}\"");
        }
    }

    // Check for duplicate task
    public static boolean noDuplicateTasks(String newTask) {
        for (int i = 0; i < taskCounter; i++) {
            if (newTask.equalsIgnoreCase(taskList[i].description)) {
                return false;
            }
        }
        return true;
    }

    // Print task added
    public static void printTaskAdded(String uniqueMessage) {
        System.out.println("Got it. I've added this task:");
        System.out.println(uniqueMessage);
        System.out.println("Now you have " + taskCounter + (taskCounter == 1 ? " task " : " tasks ") + "in the list.");
    }

    // Print duplicate task message
    public static void printDuplicateTaskMessage() {
        System.out.println("This task already exists!");
    }
}
