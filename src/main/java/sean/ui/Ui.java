package sean.ui;

import sean.commands.CommandResult;
import sean.common.Messages;

import java.io.PrintWriter;
import java.util.Scanner;

public class Ui {
    // Attributes
    private final Scanner in;
    private final PrintWriter out;

    // Constructor
    public Ui() {
        this.in = new Scanner(System.in);
        this.out = new PrintWriter(System.out, true);
    }

    // Methods
    public void printToUser(String... message) {
        for (String s : message) {
            out.println(s);
        }
    }

    public void printResultToUser(CommandResult commandResult) {
        printToUser(commandResult.commandResultToUser);
        if (commandResult.taskList != null) {
            int taskListSize = commandResult.taskList.getTaskList().size();
            for (int i = 0; i < taskListSize; i++) {
                printToUser(i + 1 + ". " + commandResult.taskList.getTaskList().get(i).toString());
            }
        }
    }

    public void printWelcomeMessage() {
        printToUser(Messages.WELCOME_MESSAGE);
    }

    public void showLoadingError() {
        printToUser(Messages.LOADING_ERROR_MESSAGE);
    }

    public String readUserCommand() {
        String input = in.nextLine();

        // Ignore empty inputs
        while (input.trim().isEmpty()) {
            input = in.nextLine();
        }

        return input;
    }
}
