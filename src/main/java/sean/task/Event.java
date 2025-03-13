package sean.task;

public class Event extends Task {
    // Attributes
    protected String from;
    protected String to;

    // Constructor
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (super.isDone ? "1" : "0") + " | " + super.description + " | " + this.from + "-" + this.to;
    }
}
