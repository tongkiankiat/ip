package sean.task;

public class Task {
    // Attributes
    protected String description;
    protected boolean isDone;

    // Constructor
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    // Getters
    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // Methods
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toFileFormat() { return ""; }
}
