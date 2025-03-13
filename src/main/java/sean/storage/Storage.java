package sean.storage;

import sean.common.Messages;
import sean.exceptions.SeanException;
import sean.task.Task;
import sean.tasklist.TaskList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    // Attributes
    private static final Path STORAGE_FILEPATH = Paths.get(System.getProperty("user.dir"), "data", "taskList.txt");;

    // Constructor
    public Storage() {}

    /**
     * Loads tasks from the storage file and returns them as a list of parsed string arrays.
     *
     * @return A list of string arrays representing stored task data.
     * @throws SeanException If there is an error loading the task list file.
     */
    public ArrayList<String[]> load() throws SeanException {
        File file = STORAGE_FILEPATH.toFile();
        ArrayList<String[]> lineParts = new ArrayList<>();
        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    lineParts.add(line.split("\\|"));
                }
            } else {
                createTaskListFile();
            }
        } catch (IOException e) {
            throw new SeanException(Messages.LOAD_TASK_LIST_FILE_ERROR_MESSAGE);
        }
        return lineParts;
    }

    /**
     * Creates the task list file if it does not exist.
     *
     * @throws SeanException If there is an error creating the task list file.
     */
    public void createTaskListFile() throws SeanException {
        try {
            File file = STORAGE_FILEPATH.toFile();
            File parentFolder = file.getParentFile();
            // Check that /data folder exists
            if (!parentFolder.exists()) {
                parentFolder.mkdirs();
            }
            parentFolder.mkdirs();
            // Check that the taskList.txt file exists
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new SeanException(Messages.CREATE_TASK_LIST_FILE_ERROR_MESSAGE);
        }
    }

    /**
     * Saves the current task list to the storage file.
     *
     * @param taskList The {@code TaskList} containing tasks to be saved.
     * @throws SeanException If there is an error saving the task list.
     */
    public void saveTaskList(TaskList taskList) throws SeanException {
        try {
            File file = STORAGE_FILEPATH.toFile();
            File parentFolder = file.getParentFile();

            if (!parentFolder.exists()) {
                parentFolder.mkdirs();
            }

            ArrayList<Task> taskArrayList = taskList.getTaskList();
            ArrayList<String> fileLines = new ArrayList<>();
            for (Task task : taskArrayList) {
                fileLines.add(task.toFileFormat());
            }

            // Rewrite back to the file
            Files.write(STORAGE_FILEPATH, fileLines);
        } catch (IOException e) {
            throw new SeanException(Messages.SAVE_OR_UPDATE_TASK_LIST_FILE_ERROR_MESSAGE);
        }
    }
}
