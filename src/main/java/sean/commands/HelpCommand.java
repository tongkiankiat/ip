package sean.commands;

public class HelpCommand extends Command {
    // Attributes
    private static final String HELP_MESSAGE = "Here are the commands available:";

    // Constructor
    public HelpCommand() {}

    // Methods
    @Override
    public CommandResult executeCommand() {
        return new CommandResult(HELP_MESSAGE);
    }
}
