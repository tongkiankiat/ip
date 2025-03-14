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

    /**
     * Constructs a {@code Sean} chatbot instance, initializing UI, storage, and task management.
     * Loads tasks from storage if available; otherwise, initializes a new task list.
     */
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

    /**
     * The main entry point for the Sean CLI application.
     *
     * @param args Command-line arguments (not used).
     * @throws SeanException If an error occurs during execution.
     */
    public static void main(String[] args) throws SeanException {
        new Sean().run();
    }

    /**
     * Runs the chatbot, processing user commands in a loop until the user exits.
     *
     * @throws SeanException If an error occurs during command execution.
     */
    public void run() throws SeanException {
        ui.printWelcomeMessage();
        Command command = null;
        do {
            String userCommand = ui.readUserCommand();
            try {
                command = new Parser().parseUserCommand(userCommand);
                CommandResult commandResult = execute(command);
                ui.printResultToUser(commandResult);
            } catch (SeanException e) {
                ui.printToUser(e.getMessage());
            }
        } while (!ByeCommand.isBye(command));
    }

    /**
     * Executes a given command, updates the task list, and saves the updated task list to the user's device..
     *
     * @param command The command to be executed.
     * @return The result of executing the command.
     * @throws SeanException If an error occurs during command execution.
     */
    public CommandResult execute(Command command) throws SeanException {
        command.setTaskList(tasks);
        CommandResult commandResult = command.executeCommand();
        storage.saveTaskList(tasks);
        return commandResult;
    }
}
