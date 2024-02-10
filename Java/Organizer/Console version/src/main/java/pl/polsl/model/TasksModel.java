/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pl.polsl.exceptions.IdNotExistsException;

/**
 * Model class representing a collection of tasks with various operations.
 *
 * @author Konrad Rduch
 * @version f2
 * @since p1
 */
public class TasksModel {

    private List<Task> tasks = new ArrayList<>(); //List of tasks associated with this model.

    /**
     * Default constructor for TasksModel.
     */
    public TasksModel() {
    }

    /**
     * Constructor for TasksModel with an initial list of tasks.
     *
     * @param tasks The list of tasks to initialize the model.
     */
    public TasksModel(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        if(task!=null)tasks.add(task);
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Edits a task in the list based on the provided task.
     *
     * @param task The task with updated information.
     * @throws IdNotExistsException If the task with the given ID does not
     * exist.
     */
    public void editTask(Task task) throws IdNotExistsException {
        if(task==null)return;
        
        int taskId = task.getId();

        if (!checkId(taskId - 1)) {
            throw new IdNotExistsException("Task with the given ID does not exist.");
        }

        this.tasks.get(taskId - 1).setDescription(task.getDescription());
        this.tasks.get(taskId - 1).setDone(task.isDone());
    }

    /**
     * Deletes a task from the list based on the provided task ID.
     *
     * @param taskId The ID of the task to be deleted.
     * @throws IdNotExistsException If the task with the given ID does not
     * exist.
     */
    public void deleteTask(int taskId) throws IdNotExistsException {
        int indexToDelete = -1;

        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getId() == taskId) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            throw new IdNotExistsException("Task with the given ID does not exist.");
        }

        this.tasks.remove(indexToDelete);
    }

    /**
     * Checks if the provided task ID is within the valid range.
     *
     * @param number The task ID to be checked.
     * @return True if the ID is valid, false otherwise.
     */
    private boolean checkId(int number) {
        return number >= 0 && number < tasks.size();
    }

    /**
     * Toggles the completion status of a task based on the provided task ID.
     *
     * @param number The ID of the task to toggle.
     * @throws IdNotExistsException If the task with the given ID does not
     * exist.
     */
    public void toggleTaskStatus(int number) throws IdNotExistsException {
        int indexToToggle = -1;

        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getId() == number) {
                indexToToggle = i;
                break;
            }
        }

        if (indexToToggle == -1) {
            throw new IdNotExistsException("Task with the given ID does not exist.");
        }

        this.tasks.get(indexToToggle).toggle();
    }

    /**
     * Filters tasks based on their completion status.
     *
     * @param done True for completed tasks, false for uncompleted tasks.
     * @return The list of tasks matching the specified completion status.
     */
    public List<Task> filterTasksByStatus(boolean done) {
        List<Task> filteredTasks = new ArrayList<>();

        // Filtering tasks based on the completion status
        filteredTasks = tasks.stream()
                .filter(task -> task.isDone() == done)
                .collect(Collectors.toList());

        return filteredTasks;
    }
}
