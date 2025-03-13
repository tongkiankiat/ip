package sean.common;

public class Messages {
    public static final String WELCOME_MESSAGE = "Hello! I'm Sean\nWhat can i do for you?";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String LOADING_ERROR_MESSAGE = "Error loading task list file.";
    public static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command, did you mistype a command?";
    public static final String NO_TASKS_MESSAGE = "You have no tasks!";
    public static final String LIST_TASKS_MESSAGE = "Here are the tasks in your list:";
    public static final String TASK_ALREADY_MARKED_MESSAGE = "Task is already marked.";
    public static final String TASK_ALREADY_UNMARKED_MESSAGE = "Task is already unmarked.";
    public static final String TASK_MARKED_MESSAGE = "Nice! I've marked this task as done!";
    public static final String TASK_UNMARKED_MESSAGE = "OK, I've unmarked this task as not done.";
    public static final String INVALID_TASK_NUMBER_MESSAGE = "Invalid task number! Please input a test number from 1 to ";
    public static final String OUT_OF_RANGE_TASK_NUMBER_MESSAGE = "The task number is not valid! Are you sure you entered a number?";
    public static final String ADD_TODO_TASK_ERROR_MESSAGE = "Please add a description for the todo task in the format: \"todo {task}\"";
    public static final String ADD_DEADLINE_TASK_ERROR_MESSAGE = "Please add a description and deadline for the deadline task in the format: \"deadline {task} /by {date/time}\"";
    public static final String ADD_EVENT_TASK_ERROR_MESSAGE = "Please add a description and a start and end date/time for the event task in the format: \"event {task} /from {date/time} /to {date/time}\"";
    public static final String TASK_ADDED_MESSAGE = "Got it. I've added this task.";
    public static final String TASK_DELETED_MESSAGE = "Noted. I've removed this task.";
    public static final String TASKS_FOUND_MESSAGE = "Here are the matching tasks in your list:";
    public static final String NO_TASKS_FOUND_MESSAGE = "There are no such tasks with keyword: ";
    public static final String TASKS_FOUND_ERROR_MESSAGE = "Error finding files with keyword: ";
    public static final String TASK_DUPLICATE_MESSAGE = "This task already exists!";
    public static final String CREATE_TASK_LIST_FILE_ERROR_MESSAGE = "There was an error creating the task list file.";
    public static final String LOAD_TASK_LIST_FILE_ERROR_MESSAGE = "Error reading file.";
    public static final String SAVE_OR_UPDATE_TASK_LIST_FILE_ERROR_MESSAGE = "An error occurred saving the task list file.";
}
