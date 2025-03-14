package sean.commands;

import sean.common.Messages;
import sean.exceptions.SeanException;
import sean.task.Task;

public class MarkTaskCommand extends Command {
    // Attributes
    public int taskIndex;
    public boolean isDone;
    private final String newLine = "\n  ";

    // Constructor
    public MarkTaskCommand(int taskIndex, boolean isDone) {
        this.taskIndex = taskIndex;
        this.isDone = isDone;
    }

    // Methods
    @Override
    public CommandResult executeCommand() throws SeanException{
        String messageToReturn;
        try {
            Task task = taskList.getTaskList().get(taskIndex);
            if (task.getIsDone() == isDone) {
                return new CommandResult(Messages.TASK_ALREADY_MARKED_MESSAGE);
            }
            task.markAsDone();
            messageToReturn = Messages.TASK_MARKED_MESSAGE + newLine + task.toString();
        } catch (NumberFormatException e) {
            messageToReturn = Messages.OUT_OF_RANGE_TASK_NUMBER_MESSAGE;
        } catch (IndexOutOfBoundsException e) {
            messageToReturn = Messages.INVALID_TASK_NUMBER_MESSAGE + taskList.getTaskList().size();
        }
        return new CommandResult(messageToReturn);
    }
}
