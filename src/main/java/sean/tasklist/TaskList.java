package sean.tasklist;

import sean.parser.Parser;
import sean.task.Task;

import java.util.ArrayList;

public class TaskList {
    // Attributes
    private final ArrayList<Task> taskList;

    // Constructor
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<String[]> taskList) {
        this.taskList = Parser.parseTaskFromFile(taskList);
    }

    // Getters
    public ArrayList<Task> getTaskList() {
        return new ArrayList<>(taskList);
    }

    // Methods
    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int taskIndex) {
        taskList.remove(taskIndex);
    }

    public TaskList findTask(String keyword) {
        TaskList filteredTaskList = new TaskList();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                filteredTaskList.addTask(task);
            }
        }
        return filteredTaskList;
    }
}
