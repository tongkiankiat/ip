package sean.todo;

import sean.task.Task;

public class Todo extends Task {
    // Attributes
    protected String task;
    protected boolean isDone;

    // Constructor
    public Todo(String description, String task ) {
        super(description);
        this.task = task;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
