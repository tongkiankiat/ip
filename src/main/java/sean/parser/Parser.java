package sean.parser;

import sean.commands.*;
import sean.task.Deadline;
import sean.task.Event;
import sean.task.Task;
import sean.task.Todo;

import java.util.ArrayList;

public class Parser {
    /**
     * Parses task data from a file and converts it into a list of {@code Task} objects.
     *
     * @param lineParts A list of string arrays, where each array represents a task's data read from a file.
     * @return A list of {@code Task} objects reconstructed from the file data.
     */
    public static ArrayList<Task> parseTaskFromFile(ArrayList<String[]> lineParts) {
        ArrayList<Task> taskList = new ArrayList<>();
        for (String[] line : lineParts) {
            String taskType = line[0].trim();
            boolean isDone = line[1].trim().equals("1");
            String taskDescription = line[2].trim();

            if (taskType.equals("T")) { // Todo task
                taskList.add(new Todo(taskDescription, taskDescription, isDone));
            } else if (taskType.equals("D")) { // Deadline task
                taskList.add(new Deadline(taskDescription, line[3].trim(), isDone));
            } else { // Event task
                taskList.add(new Event(taskDescription, line[3].split("-")[0].trim(), line[3].split("-")[1].trim(), isDone));
            }
        }
        return taskList;
    }

    /**
     * Parses a user's command input and returns the corresponding {@code Command} object to be executed.
     *
     * @param userCommand The raw input string entered by the user.
     * @return A {@code Command} object representing the parsed user command.
     */
    public Command parseUserCommand(String userCommand) {
        String commandWord = userCommand.split(" ")[0].trim();

        if (commandWord.equals("bye")) {
            return new ByeCommand();
        } else if (commandWord.equals("list")) {
            return new ListCommand();
        } else if (commandWord.equals("unmark")) {
            int taskIndex = Integer.parseInt(userCommand.split(" ")[1].trim()) - 1;
            boolean isDone = false;
            return new UnmarkTaskCommand(taskIndex, isDone);
        } else if (commandWord.equals("mark")) {
            int taskIndex = Integer.parseInt(userCommand.split(" ")[1].trim()) - 1;
            boolean isDone = false;
            return new MarkTaskCommand(taskIndex, isDone);
        } else if (commandWord.equals("delete")) {
            int taskIndex = Integer.parseInt(userCommand.split(" ")[1].trim()) - 1;
            return new DeleteCommand(taskIndex);
        } else if (commandWord.equals("todo")) {
            String todoTask = userCommand.split("todo")[1].strip();
            return new TodoCommand(todoTask);
        } else if (commandWord.equals("deadline")) {
            String deadlineTask = userCommand.split("deadline | /by")[1].trim();
            String deadlineBy = userCommand.split("by")[1].strip();
            return new DeadlineCommand(deadlineTask, deadlineBy);
        } else if (commandWord.equals("event")) {
            String eventTask = userCommand.split("event | /from | /to")[1].trim();
            String eventFrom = userCommand.split("/from | /to")[1].trim();
            String eventTo = userCommand.split("/to")[1].trim();
            return new EventCommand(eventTask, eventFrom, eventTo);
        } else {
            return new UnknownCommand();
        }
    }
}