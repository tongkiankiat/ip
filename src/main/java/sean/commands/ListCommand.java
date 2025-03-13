package sean.commands;

import sean.common.Messages;
import sean.tasklist.TaskList;

public class ListCommand extends Command {
    // Constructor
    public ListCommand() {}

    // Methods
    @Override
    public CommandResult executeCommand() {
        TaskList tasks = taskList;
        int taskListSize = taskList.getTaskList().size();
        return new CommandResult(taskListSize > 0 ? Messages.LIST_TASKS_MESSAGE : Messages.NO_TASKS_MESSAGE, tasks);
    }
}
