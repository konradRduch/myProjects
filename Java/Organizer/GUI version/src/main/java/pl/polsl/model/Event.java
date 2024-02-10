/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import java.util.Date;

/**
 * Represents an event with a unique identifier, title, start and end dates, and a description.
 * 
 * @author Konrad Rduch
 * @version f3
 * @since p1
 */
public class Event {
    private int id; // Unique identifier for the event
    private String title; // Title of the event
    private Date startDate; // Start date of the event
    private Date endDate; // End date of the event
    private String description; // Description of the event

    /**
     * Default constructor for the Event class.
     */
    public Event() {
        
    }
    
    /**
     * Parameterized constructor to initialize an Event with specified attributes.
     * 
     * @param id The unique identifier of the event.
     * @param title The title of the event.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     * @param description The description of the event.
     */
    public Event(int id, String title, Date startDate, Date endDate, String description) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    /**
     * Retrieves the unique identifier of the event.
     * 
     * @return The unique identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the event.
     * 
     * @param id The unique identifier to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the title of the event.
     * 
     * @return The title of the event.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the event.
     * 
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the start date of the event.
     * 
     * @return The start date of the event.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the event.
     * 
     * @param startDate The start date to set.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Retrieves the end date of the event.
     * 
     * @return The end date of the event.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the event.
     * 
     * @param endDate The end date to set.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Retrieves the description of the event.
     * 
     * @return The description of the event.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the event.
     * 
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
