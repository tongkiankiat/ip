package sean.commands;

import sean.common.Messages;
import sean.exceptions.SeanException;
import sean.task.Task;
import sean.tasklist.TaskList;

public class Command {
    // Attributes
    protected TaskList taskList;

    // Constructor
    public Command(TaskList taskList) {
        this.taskList = taskList;
    }

    protected Command() {}

    // Setter
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    // Methods
    public CommandResult executeCommand() throws SeanException {
        return new CommandResult("");
    }

    public boolean duplicateTask(String newTask) {
        for (Task task : taskList.getTaskList()) {
            if (newTask.equalsIgnoreCase(task.getDescription())) {
                return true;
            }
        }
        return false;
    }

    public String taskAddedMessage(String uniqueMessage) {
        int taskListSize = taskList.getTaskList().size();
        return Messages.TASK_ADDED_MESSAGE + '\n' + uniqueMessage + '\n' + "Now you have " + taskListSize + (taskListSize == 1 ? " task " : " tasks ") + "in the list.";
    }

    public String taskDeletedMessage(String uniqueMessage) {
        int taskListSize = taskList.getTaskList().size();
        return Messages.TASK_DELETED_MESSAGE + "\n" + "  " + uniqueMessage + "\n" + "Now you have " + taskListSize + (taskListSize == 1 ? " task " : " tasks ") + "in the list.";
    }
}
