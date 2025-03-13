package sean;

import sean.parser.Parser;
import sean.exceptions.SeanException;
import sean.ui.Ui;
import sean.tasklist.TaskList;
import sean.storage.Storage;
import sean.commands.*;

public class Sean {
    // Initialise Classes
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // Constructor
    public Sean() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (SeanException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    // Methods
    public static void main(String[] args) throws SeanException {
        new Sean().run();
    }

    public void run() throws SeanException {
        ui.printWelcomeMessage();
        Command command;
        do {
            String userCommand = ui.readUserCommand();
            command = new Parser().parseUserCommand(userCommand);
            CommandResult commandResult = execute(command);
            ui.printResultToUser(commandResult);
        } while (!ByeCommand.isBye(command));
    }

    public CommandResult execute(Command command) throws SeanException {
        command.setTaskList(tasks);
        CommandResult commandResult = command.executeCommand();
        storage.saveTaskList(tasks);
        return commandResult;
    }
}
