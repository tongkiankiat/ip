package sean.commands;

import sean.common.Messages;
import sean.task.Todo;

public class TodoCommand extends Command {
    // Attributes
    public String todoTask;

    // Constructor
    public TodoCommand(String todoTask) {
        this.todoTask = todoTask;
    }

    // Methods
    @Override
    public CommandResult executeCommand() {
        String messageToReturn;
        try {
            if (!duplicateTask(todoTask)) {
                Todo todo = new Todo(todoTask, todoTask, false);
                taskList.addTask(todo);
                messageToReturn = taskAddedMessage(todoTaskMessage());
            } else {
                messageToReturn = Messages.TASK_DUPLICATE_MESSAGE;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            messageToReturn = Messages.ADD_TODO_TASK_ERROR_MESSAGE;
        }
        return new CommandResult(messageToReturn);
    }

    public String todoTaskMessage() {
        return "  [T][ ] " + todoTask;
    }
}
