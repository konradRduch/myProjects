/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

/**
 * Represents a task with an ID, description, and completion status.
 *
 * @author Konrad Rduch
 * @version f2
 * @since p1
 */
public class Task {

    private int id;                  // Unique identifier for the task
    private String description;      // Description of the task
    private boolean done;            // Completion status of the task

    /**
     * Gets the unique identifier of the task.
     *
     * @return The ID of the task.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the task.
     *
     * @param id The ID to be set for the task.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description for the task.
     *
     * @param description The description to be set for the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Sets the completion status for the task.
     *
     * @param done The completion status to be set for the task.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Constructs a task with the specified ID, description, and completion
     * status.
     *
     * @param id The unique identifier for the task.
     * @param description The description of the task.
     * @param done The completion status of the task.
     */
    public Task(int id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    /**
     * Default constructor for the Task class.
     */
    public Task() {
    }

    /**
     * Toggles the completion status of the task. If the task is marked as done,
     * it will be marked as not done, and vice versa.
     */
    public void toggle() {
        this.done = !this.done;
    }
    
  
}
