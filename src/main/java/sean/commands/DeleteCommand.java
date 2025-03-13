package sean.commands;

import sean.common.Messages;

public class DeleteCommand extends Command {
    // Attributes
    public int taskIndex;

    // Constructor
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    // Methods
    @Override
    public CommandResult executeCommand() {
        String messageToReturn;
        try {
            String taskDescription = taskList.getTaskList().get(taskIndex).toString(); // 0-indexed
            taskList.removeTask(taskIndex);
            messageToReturn = taskDeletedMessage(taskDescription);
        } catch (NumberFormatException e) {
            messageToReturn = Messages.OUT_OF_RANGE_TASK_NUMBER_MESSAGE;
        } catch (IndexOutOfBoundsException e) {
            messageToReturn = Messages.INVALID_TASK_NUMBER_MESSAGE + taskList.getTaskList().size();
        }
        return new CommandResult(messageToReturn);
    }
}
