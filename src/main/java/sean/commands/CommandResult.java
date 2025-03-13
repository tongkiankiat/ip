package sean.commands;

import sean.tasklist.TaskList;

public class CommandResult {
    // Attributes
    public final String commandResultToUser;
    public final TaskList taskList;

    // Constructor
    public CommandResult(String commandResultToUser) {
        this.commandResultToUser = commandResultToUser;
        this.taskList = null;
    }

    public CommandResult(String commandResultToUser, TaskList taskList) {
        this.commandResultToUser = commandResultToUser;
        this.taskList = taskList;
    }
}
