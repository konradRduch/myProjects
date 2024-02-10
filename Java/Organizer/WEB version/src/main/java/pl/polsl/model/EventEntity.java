/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * Represents an entity that encapsulates information about an event, including
 * its title, start date, end date, description, and unique identifier.
 *
 * @author Konrad Rduch
 * @version f5
 * @since f5
 */
@Entity
public class EventEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title; // Title of the event
    private Date startDate; // Start date of the event
    private Date endDate; // End date of the event
    private String description; // Description of the event

    /**
     * Gets the title of the event.
     *
     * @return The title of the event.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title for the event.
     *
     * @param title The title to be set for the event.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the start date of the event.
     *
     * @return The start date of the event.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date for the event.
     *
     * @param startDate The start date to be set for the event.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date of the event.
     *
     * @return The end date of the event.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date for the event.
     *
     * @param endDate The end date to be set for the event.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the description of the event.
     *
     * @return The description of the event.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description for the event.
     *
     * @param description The description to be set for the event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the unique identifier of the event.
     *
     * @return The unique identifier of the event.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the event.
     *
     * @param id The unique identifier to be set for the event.
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
        if (!(object instanceof EventEntity)) {
            return false;
        }
        EventEntity other = (EventEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.polsl.model.EventEntity[ id=" + id + " ]";
    }

}
