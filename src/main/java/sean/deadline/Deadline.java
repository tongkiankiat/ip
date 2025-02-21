package sean.deadline;

import sean.task.Task;

public class Deadline extends Task {
    // Attributes
    protected String by;

    // Constructor
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + this.by + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (super.isDone ? "1" : "0") + " | " + super.description + " | " + this.by;
    }
}
