package sean.commands;

import sean.common.Messages;

public class ByeCommand extends Command {
    // Methods
    @Override
    public CommandResult executeCommand() {
        return new CommandResult(Messages.GOODBYE_MESSAGE);
    }

    public static boolean isBye(Command command) {
        return command instanceof ByeCommand;
    }
}
