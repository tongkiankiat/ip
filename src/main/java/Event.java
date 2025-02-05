public class Event extends Task {
    // Attributes
    protected String from;
    protected String to;
    protected boolean isDone;

    // Constructor
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (from: " + from + " to:" + to + ")";
    }
}
