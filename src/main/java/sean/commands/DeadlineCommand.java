package sean.commands;

import sean.common.Messages;
import sean.task.Deadline;

public class DeadlineCommand extends Command {
    // Attributes
    public String deadlineTask;
    public String deadlineBy;

    // Constructor
    public DeadlineCommand(String deadlineTask, String deadlineBy) {
        this.deadlineTask = deadlineTask;
        this.deadlineBy = deadlineBy;
    }

    // Methods
    @Override
    public CommandResult executeCommand() {
        String messageToReturn;
        try {
            if (!duplicateTask(deadlineTask)) {
                Deadline deadline = new Deadline(deadlineTask, deadlineBy, false);
                taskList.addTask(deadline);
                messageToReturn = taskAddedMessage(deadlineTaskMessage());
            } else {
                messageToReturn = Messages.TASK_DUPLICATE_MESSAGE;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            messageToReturn = Messages.ADD_DEADLINE_TASK_ERROR_MESSAGE;
        }
        return new CommandResult(messageToReturn);
    }

    public String deadlineTaskMessage() {
        return "  [D][ ] " + deadlineTask + " (by: " + deadlineBy + ")";
    }
}
