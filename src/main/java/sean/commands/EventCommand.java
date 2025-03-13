package sean.commands;

import sean.common.Messages;
import sean.task.Event;

public class EventCommand extends Command {
    // Attributes
    public String eventTask;
    public String eventFrom;
    public String eventTo;

    // Constructor
    public EventCommand(String eventTask, String eventFrom, String eventTo) {
        this.eventTask = eventTask;
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }

    // Methods
    @Override
    public CommandResult executeCommand() {
        String messageToReturn;
        try {
            if (!duplicateTask(eventTask)) {
                Event event = new Event(eventTask, eventFrom, eventTo, false);
                taskList.addTask(event);
                messageToReturn = taskAddedMessage(eventTaskMessage());
            } else {
                messageToReturn = Messages.TASK_DUPLICATE_MESSAGE;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            messageToReturn = Messages.ADD_EVENT_TASK_ERROR_MESSAGE;
        }
        return new CommandResult(messageToReturn);
    }

    public String eventTaskMessage() {
        return "  [E][ ] " + eventTask + " (from: " + eventFrom + " to: " + eventTo + ")";
    }
}
