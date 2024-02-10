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
 * Singleton class representing the TasksModel, managing a list of tasks. The
 * class follows the Singleton design pattern to ensure a single instance is
 * created and used.
 *
 * @author Konrad Rduch
 * @version f4
 * @since p1
 */
public class TasksModel {

    private static TasksModel instance;// Singleton instance

    private List<Task> tasks = new ArrayList<>(); //List of tasks associated with this model.

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private TasksModel() {
        // Private constructor to prevent instantiation from outside the class.
    }

    /**
     * Returns the singleton instance of the TasksModel. If the instance does
     * not exist, it creates a new one.
     *
     * @return The singleton instance of the TasksModel.
     */
    public static TasksModel getInstance() {
        if (instance == null) {
            instance = new TasksModel();
        }
        return instance;
    }

    /**
     * Sets the list of tasks associated with this model.
     *
     * @param tasks The list of tasks to set for the model.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        if (task != null) {
            tasks.add(task);
        }
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

        if (task == null) {
            return;
        }

        int taskId = task.getId();

        int indexToEdit = findTaskIndexById(taskId);

        if (indexToEdit == -1) {
            throw new IdNotExistsException("Task with the given ID does not exist.");
        }

        this.tasks.get(indexToEdit).setDescription(task.getDescription());
        this.tasks.get(indexToEdit).setDone(task.isDone());

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

    /**
     * Returns the maximum index of tasks in the collection. If the collection
     * is empty, it returns a default index of 1. The index is determined based
     * on the maximum task ID present in the collection.
     *
     * @return The maximum index of tasks incremented by 1, or Integer.MIN_VALUE
     * + 1 if the collection is empty.
     */
    public int getMaxIndex() {
        return tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;
    }

    /**
     * Finds the index of a task with the specified ID in the collection.
     *
     * @param taskId The ID of the task to find in the collection.
     * @return The index of the task with the specified ID if found, or -1 if
     * not found.
     */
    private int findTaskIndexById(int taskId) {
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getId() == taskId) {
                return i;
            }
        }
        return -1;
    }

}
