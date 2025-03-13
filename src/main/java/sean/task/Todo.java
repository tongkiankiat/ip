package sean.task;

public class Todo extends Task {
    // Attributes
    protected String task;

    // Constructor
    public Todo(String description, String task, boolean isDone) {
        super(description, isDone);
        this.task = task;
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }

    @Override
    public String toFileFormat() {
        return "T | " + (super.isDone ? "1" : "0") + " | " + super.description;
    }
}
