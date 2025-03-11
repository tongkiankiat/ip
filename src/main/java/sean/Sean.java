package sean;

import sean.deadline.Deadline;
import sean.event.Event;
import sean.exceptions.SeanException;
import sean.task.Task;
import sean.todo.Todo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Sean {
    // Initialise Array to store Tasks
    public static ArrayList<Task> taskList = new ArrayList<>();

    // Filepath of the file task list
    // We store the task list file in: /data/taskList.txt
    private static final String dir = System.getProperty("user.dir");
    public static final Path filePath = Paths.get(dir, "data", "taskList.txt" );

    public static void main(String[] args) throws SeanException {
        String input;
        Scanner in = new Scanner(System.in);

        // Load task list from taskList.txt
        loadTaskList();

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
            // Deleting a task
            else if (input.startsWith("delete")) {
                deleteTask(input);
            }
            // Else we move on to todo, deadline, or task prompts
            else if (input.startsWith("todo")) {
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
        in.close();
    }

    // Display task list
    public static void displayTaskList() {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
        }
    }

    // Marking/Unmarking Task
    public static void markTask(String input, boolean isDone) throws SeanException {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = taskList.get(taskIndex);

            if (task.getIsDone() == isDone) {
                throw new SeanException((isDone ? "Task is already marked." : "Task is already unmarked"));
            }

            if (isDone) {
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                task.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            // Update the task list file to reflect correct status
            saveOrUpdateTaskList(task, taskIndex);

            System.out.println("  " + task.toString());
        } catch (NumberFormatException e) {
            System.out.println("The task number is not valid! Are you sure you entered a number?");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number! Please input a test number from 1 to " + taskList.size());
        }
    }

    // Add todo task
    public static void addTodoTask(String input) {
        try {
            String todoTask = input.split("todo")[1].strip();
            if (noDuplicateTasks(todoTask)) {
                Task task = new Todo(todoTask, todoTask, false);
                taskList.add(task);
                printTaskAdded("  [T][ ] " + todoTask);
                saveOrUpdateTaskList(task, null);
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
                Deadline deadline = new Deadline(deadlineTask, deadlineBy, false);
                taskList.add(deadline);
                printTaskAdded("  [D][ ] " + deadlineTask + " (by: " + deadlineBy + ")");
                saveOrUpdateTaskList(deadline, null);
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
                Event event = new Event(eventTask, eventFrom, eventTo, false);
                taskList.add(event);
                printTaskAdded("  [E][ ] " + eventTask + " (from: " + eventFrom + " to: " + eventTo + ")");
                saveOrUpdateTaskList(event, null);
            } else {
                printDuplicateTaskMessage();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please add a description and a start and end date/time for the event task in the format: \"event {task} /from {date/time} /to {date/time}\"");
        }
    }

    // Deleting a task
    public static void deleteTask(String input) {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]);
            String taskDescription = taskList.get(taskIndex - 1).toString();
            taskList.remove(taskIndex - 1);
            printTaskDeleted(taskDescription);
            removeFromTaskList(taskIndex - 1);
        } catch (NumberFormatException e) {
            System.out.println("The task number is not valid! Are you sure you entered a number?");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number! Please input a task number from 1 to " + taskList.size());
        }
    }

    // Check for duplicate task
    public static boolean noDuplicateTasks(String newTask) {
        for (Task task : taskList) {
            if (newTask.equalsIgnoreCase(task.getDescription())) {
                return false;
            }
        }
        return true;
    }

    // Print task added
    public static void printTaskAdded(String uniqueMessage) {
        System.out.println("Got it. I've added this task:");
        System.out.println(uniqueMessage);
        System.out.println("Now you have " + taskList.size() + (taskList.size() == 1 ? " task " : " tasks ") + "in the list.");
    }

    // Print task deleted
    public static void printTaskDeleted(String uniqueMessage) {
        System.out.println("Noted. I've removed this task.");
        System.out.println("  " + uniqueMessage);
        System.out.println("Now you have " + taskList.size() + (taskList.size() == 1 ? " task " : " tasks ") + "in the list.");
    }

    // Print duplicate task message
    public static void printDuplicateTaskMessage() {
        System.out.println("This task already exists!");
    }

    // Create the file to store tasks, if it does not exist yet
    public static void createTaskListFile() {
        try {
            File file = filePath.toFile();
            File parentFolder = file.getParentFile();
            // Check that /data folder exists
            if (!parentFolder.exists()) {
                parentFolder.mkdirs();
            }
            // Check that the taskList.txt file exists
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("There was an error creating the task list file.");
        }
    }

    // Loads tasks in hard disk
    public static void loadTaskList() {
        // Clear taskList first
        taskList.clear();
        File file = filePath.toFile();
        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] lineParts = line.split("\\|");
                    parseTaskFromFile(lineParts);
                }
            } else {
                createTaskListFile();
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    // Parse text input from taskList.txt
    public static void parseTaskFromFile(String[] lineParts) {
        String taskType = lineParts[0].trim();
        boolean isDone = lineParts[1].trim().equals("1");
        String taskDescription = lineParts[2].trim();

        if (taskType.equals("T")) { // Todo task
            Todo todo = new Todo(taskDescription, taskDescription, isDone);
            taskList.add(todo);
        } else if (taskType.equals("D")) { // Deadline task
            Deadline deadline = new Deadline(taskDescription, lineParts[3].trim(), isDone);
            taskList.add(deadline);
        } else { // Event task
            Event event = new Event(taskDescription, lineParts[3].trim(), lineParts[3].split("-")[1].trim(), isDone);
            taskList.add(event);
        }
    }

    // Saves or updates task list
    public static void saveOrUpdateTaskList(Task task, Integer taskIndex) {
        try {
            // We append a new task to the file when index is null, else we modify the status of the task
            File file = filePath.toFile();
            File parentFolder = file.getParentFile();
            // Check that parent folder exists
            if (!parentFolder.exists()) {
                parentFolder.mkdirs();
            }

            ArrayList<String> fileLines = new ArrayList<>(Files.readAllLines(file.toPath()));
            String taskFileFormat = task.toFileFormat();

            if (taskIndex == null) {
                // Append new task
                fileLines.add(taskFileFormat);
            } else {
                // Go to the line by using the index provided, and update its status
                fileLines.set(taskIndex, taskFileFormat);
            }
            // Rewrite back to the file
            Files.write(filePath, fileLines);
        } catch (IOException e) {
            System.out.println("An error occurred saving or updating the task list file.");
        }
    }

    // Delete task from task list
    public static void removeFromTaskList(int taskIndex) {
        try {
            // Open the file and store all its lines in an ArrayList
            File file = filePath.toFile();
            ArrayList<String> fileLines = new ArrayList<>(Files.readAllLines(file.toPath()));

            // Remove the task at the specified taskIndex
            fileLines.remove(taskIndex);

            // Rewrite back to file
            Files.write(filePath, fileLines);
        } catch (IOException e) {
            System.out.println("An error occurred deleting the task from the task list file");
        }
    }
}
