package sean.tasklist;

import sean.parser.Parser;
import sean.task.Task;

import java.util.ArrayList;

public class TaskList {
    // Attributes
    private final ArrayList<Task> taskList;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} by parsing task data from a list of string arrays.
     *
     * @param taskList A list of string arrays representing tasks from storage.
     */
    public TaskList(ArrayList<String[]> taskList) {
        this.taskList = Parser.parseTaskFromFile(taskList);
    }

    /**
     * Returns a copy of the current task list.
     *
     * @return A new {@code ArrayList} containing all tasks in the list.
     */
    public ArrayList<Task> getTaskList() {
        return new ArrayList<>(taskList);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the task list based on its index.
     *
     * @param taskIndex The index of the task to be removed.
     */
    public void removeTask(int taskIndex) {
        taskList.remove(taskIndex);
    }
}
