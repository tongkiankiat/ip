package sean.deadline;

import sean.task.Task;

public class Deadline extends Task {
    // Attributes
    protected String by;
    protected boolean isDone;

    // Constructor
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + by + ")";
    }
}
