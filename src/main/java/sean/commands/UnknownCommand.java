package sean.commands;

import sean.common.Messages;

public class UnknownCommand extends Command {
    // Constructor
    public UnknownCommand() {}

    // Methods
    @Override
    public CommandResult executeCommand() {
        return new CommandResult(Messages.UNKNOWN_COMMAND_MESSAGE);
    }
}
