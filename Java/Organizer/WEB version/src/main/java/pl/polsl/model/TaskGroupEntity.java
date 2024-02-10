/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import jakarta.persistence.CascadeType;
import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 *
 * Represents a TaskGroup entity with attributes such as identifier, name,
 * completion status, and a list of associated tasks.
 *
 * @author Konrad Rduch
 * @version f5
 * @since f5
 */
@Entity
public class TaskGroupEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private boolean done;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group", orphanRemoval = true)
    private List<TaskEntity> groupTask;

    /**
     * Gets a list of tasks associated with the task group.
     *
     * @return A list of TaskEntity objects associated with the task group.
     */
    public List<TaskEntity> getGroupTask() {
        return groupTask;
    }

    /**
     * Sets the list of tasks associated with the task group.
     *
     * @param groupTask The list of TaskEntity objects to be associated with the
     * task group.
     */
    public void setGroupTask(List<TaskEntity> groupTask) {
        this.groupTask = groupTask;
    }

    /**
     * Gets the unique identifier of the task group.
     *
     * @return The unique identifier of the task group.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the name of the task group.
     *
     * @return The name of the task group.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for the task group.
     *
     * @param name The name to be set for the task group.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the task group is marked as done.
     *
     * @return True if the task group is marked as done, false otherwise.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Sets the completion status of the task group.
     *
     * @param done True to mark the task group as done, false to mark it as not
     * done.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Sets the unique identifier for the task group.
     *
     * @param id The unique identifier to be set for the task group.
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
        if (!(object instanceof TaskGroupEntity)) {
            return false;
        }
        TaskGroupEntity other = (TaskGroupEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.polsl.model.TaskGroupEntity[ id=" + id + " ]";
    }

}
