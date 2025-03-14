package sean.commands;

import sean.common.Messages;
import sean.tasklist.TaskList;

public class FindCommand extends Command {
    // Attributes
    public String keyword;

    // Constructor
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    // Method
    @Override
    public CommandResult executeCommand() {
        String messageToReturn;
        TaskList filteredTaskList = null;
        try {
            filteredTaskList = taskList.findTask(keyword);
            messageToReturn = !filteredTaskList.getTaskList().isEmpty() ? Messages.TASKS_FOUND_MESSAGE : Messages.NO_TASKS_FOUND_MESSAGE + keyword;
        } catch (Exception e) {
            messageToReturn = Messages.TASKS_FOUND_ERROR_MESSAGE + keyword;
        }
        return new CommandResult(messageToReturn, filteredTaskList);
    }
}
