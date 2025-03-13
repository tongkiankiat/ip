package sean.task;

public class Task {
    // Attributes
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
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

    /**
     * Returns a string representation of the task.
     *
     * @return A string containing the task's status icon and description.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task formatted for file storage.
     * This method is meant to be overridden by subclasses.
     *
     * @return An empty string by default.
     */
    public String toFileFormat() { return ""; }
}
