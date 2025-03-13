package sean.commands;

public class HelpCommand extends Command {
    // Attributes
    private static final String HELP_MESSAGE = """
            List of commands:
            1. bye
                - Exits the program.
            2. list
                - Lists all tasks.
            3. unmark <task index>
                - Mark the task as undone.
            4. mark <task index>
                - Mark the task as done.
            5. delete <task index>
                - Delete the task.
            6. todo <task>
                - Add a todo task.
            7. deadline <task> /by <by>
                - Add a deadline task with a due date.
            8. event <task> /from <from> /to <to>
                - Add an event task with a start and end date.
            9. find <keyword>
                - Finds all tasks containing the keyword.
            """;

    // Constructor
    public HelpCommand() {}

    // Methods
    @Override
    public CommandResult executeCommand() {
        return new CommandResult(HELP_MESSAGE);
    }
}
