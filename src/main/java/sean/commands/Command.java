package sean.commands;

import sean.common.Messages;
import sean.exceptions.SeanException;
import sean.task.Task;
import sean.tasklist.TaskList;

public class Command {
    protected TaskList taskList;

    /**
     * Constructs a {@code Command} with the specified task list.
     *
     * @param taskList the task list to be used by this command
     */
    public Command(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Default constructor for {@code Command}.
     */
    protected Command() {}

    /**
     * Sets the task list for this command.
     *
     * @param taskList the task list to be set
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the command and returns the result.
     *
     * @return the result of executing the command
     * @throws SeanException if an error occurs during execution
     */
    public CommandResult executeCommand() throws SeanException {
        return new CommandResult("");
    }

    /**
     * Checks if a task with the given description already exists in the task list.
     *
     * @param newTask the description of the task to check for duplication
     * @return {@code true} if a task with the same description exists, {@code false} otherwise
     */
    public boolean duplicateTask(String newTask) {
        for (Task task : taskList.getTaskList()) {
            if (newTask.equalsIgnoreCase(task.getDescription())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a message indicating that a task has been added.
     *
     * @param uniqueMessage a message describing the added task
     * @return a formatted message indicating the task has been added
     */
    public String taskAddedMessage(String uniqueMessage) {
        int taskListSize = taskList.getTaskList().size();
        return Messages.TASK_ADDED_MESSAGE + '\n' + uniqueMessage + '\n' + "Now you have " + taskListSize + (taskListSize == 1 ? " task " : " tasks ") + "in the list.";
    }

    /**
     * Generates a message indicating that a task has been deleted.
     *
     * @param uniqueMessage a message describing the deleted task
     * @return a formatted message indicating the task has been deleted
     */
    public String taskDeletedMessage(String uniqueMessage) {
        int taskListSize = taskList.getTaskList().size();
        return Messages.TASK_DELETED_MESSAGE + "\n" + "  " + uniqueMessage + "\n" + "Now you have " + taskListSize + (taskListSize == 1 ? " task " : " tasks ") + "in the list.";
    }
}
