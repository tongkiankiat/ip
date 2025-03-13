package sean.ui;

import sean.commands.CommandResult;
import sean.common.Messages;

import java.io.PrintWriter;
import java.util.Scanner;

public class Ui {
    // Attributes
    private final Scanner in;
    private final PrintWriter out;

    /**
     * Constructs a {@code Ui} object, initializing input and output streams.
     */
    public Ui() {
        this.in = new Scanner(System.in);
        this.out = new PrintWriter(System.out, true);
    }

    /**
     * Prints messages to the user.
     *
     * @param message The messages to be printed.
     */
    public void printToUser(String... message) {
        for (String s : message) {
            out.println(s);
        }
    }

    /**
     * Prints the result of a command execution to the user.
     *
     * @param commandResult The result of the executed command.
     */
    public void printResultToUser(CommandResult commandResult) {
        printToUser(commandResult.commandResultToUser);
        if (commandResult.taskList != null) {
            int taskListSize = commandResult.taskList.getTaskList().size();
            for (int i = 0; i < taskListSize; i++) {
                printToUser(commandResult.taskList.getTaskList().get(i).toString());
            }
        }
    }

    public void printWelcomeMessage() {
        printToUser(Messages.WELCOME_MESSAGE);
    }

    public void showLoadingError() {
        printToUser(Messages.LOADING_ERROR_MESSAGE);
    }

    /**
     * Reads and returns the user's command input, ignoring empty inputs.
     *
     * @return The user's command as a {@code String}.
     */
    public String readUserCommand() {
        String input = in.nextLine();

        // Ignore empty inputs
        while (input.trim().isEmpty()) {
            input = in.nextLine();
        }

        return input;
    }
}
