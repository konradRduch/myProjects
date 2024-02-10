/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents a Task entity with attributes such as group, description,
 * completion status, and identifier.
 *
 * @author Konrad Rduch
 * @version f5
 * @since f5
 */
@Entity
public class TaskEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;      // Description of the task
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "task_group_id")
    TaskGroupEntity group;

    /**
     * Gets the TaskGroupEntity associated with this task.
     *
     * @return The TaskGroupEntity associated with this task.
     */
    public TaskGroupEntity getGroup() {
        return group;
    }

    /**
     * Sets the TaskGroupEntity for this task.
     *
     * @param group The TaskGroupEntity to be associated with this task.
     */
    public void setGroup(TaskGroupEntity group) {
        this.group = group;
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
     * @return True if the task is marked as done, false otherwise.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param done True to mark the task as done, false to mark it as not done.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Gets the unique identifier of the task.
     *
     * @return The unique identifier of the task.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the task.
     *
     * @param id The unique identifier to be set for the task.
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskEntity)) {
            return false;
        }
        TaskEntity other = (TaskEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.polsl.model.TaskEntity[ id=" + id + " ]";
    }

}
