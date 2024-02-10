/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import pl.polsl.exceptions.IdNotExistsException;
import pl.polsl.exceptions.StartDateIsAfterEndDateException;

/**
 * Singleton class representing the EventsModel, managing a list of events.
 * The class follows the Singleton design pattern to ensure a single instance is created and used.
 * @author Konrad Rduch
 * @version f4
 * @since p1
 */
public class EventsModel {

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private static EventsModel instance;// Singleton instance
    private List<Event> events = new ArrayList<>();//List of events associated with this model.

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private EventsModel() {
        // Private constructor to prevent instantiation from outside the class.
    }

    /**
     * Returns the singleton instance of the EventsModel. If the instance does
     * not exist, it creates a new one.
     *
     * @return The singleton instance of the EventsModel.
     */
    public static EventsModel getInstance() {
        if (instance == null) {
            instance = new EventsModel();
        }
        return instance;
    }

    /**
     * Sets the list of events associated with this model.
     *
     * @param events The list of events to set for the model.
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * Adds an event to the list of events, checking if the start date is before
     * or equal to the end date.
     *
     * @param event The event to be added.
     * @throws StartDateIsAfterEndDateException If the start date is later than
     * the end date.
     */
    public void addEvent(Event event) throws StartDateIsAfterEndDateException {
        if (event.getStartDate().compareTo(event.getEndDate()) <= 0) {
            events.add(event);
        } else {
            throw new StartDateIsAfterEndDateException("The start date cannot be later than the end date.");
        }
    }

    /**
     * Gets the list of events.
     *
     * @return The list of events.
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Edits an event in the list based on the provided event, checking if the
     * start date is before or equal to the end date.
     *
     * @param event The event with updated information.
     * @throws IdNotExistsException If the event with the given ID does not
     * exist.
     * @throws StartDateIsAfterEndDateException If the start date is later than
     * the end date.
     */
    public void editEvent(Event event) throws IdNotExistsException, StartDateIsAfterEndDateException {

        if (event == null) {
            return;
        }

        int eventId = event.getId();

        int indexToEdit = findEventIndexById(eventId);

        if (indexToEdit == -1) {
            throw new IdNotExistsException("The event with the given ID does not exist.");
        }

        if (event.getStartDate().compareTo(event.getEndDate()) > 0) {
            throw new StartDateIsAfterEndDateException("The start date cannot be later than the end date.");
        }

        Event existingEvent = this.events.get(indexToEdit);
        existingEvent.setTitle(event.getTitle());
        existingEvent.setStartDate(event.getStartDate());
        existingEvent.setEndDate(event.getEndDate());
        existingEvent.setDescription(event.getDescription());
    }

    /**
     * Checks if the provided event ID is within the valid range.
     *
     * @param number The event ID to be checked.
     * @return True if the ID is valid, false otherwise.
     */
    private boolean checkId(int number) {
        return number >= 0 && number < events.size();
    }

    /**
     * Deletes an event from the list based on the provided event ID.
     *
     * @param number The ID of the event to be deleted.
     * @throws IdNotExistsException If the event with the given ID does not
     * exist.
     */
    public void deleteEvent(int number) throws IdNotExistsException {
        int indexToDelete = -1;

        for (int i = 0; i < this.events.size(); i++) {
            if (this.events.get(i).getId() == number) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete == -1) {
            throw new IdNotExistsException("The event with the given ID does not exist.");
        }

        this.events.remove(indexToDelete);
    }

    /**
     * Gets a list of events that have not yet started (are in the future).
     *
     * @return The list of upcoming events.
     */
    public List<Event> getIncomingEvents() {
        List<Event> incomingEvents = new ArrayList<>();
        Date currentDate = new Date();

        incomingEvents = events.stream().filter(
                event -> event.getStartDate().after(currentDate)
        ).collect(Collectors.toList());

        return incomingEvents;
    }

    /**
     * Gets a list of events that have already ended (are in the past).
     *
     * @return The list of ended events.
     */
    public List<Event> getEndedEvents() {
        List<Event> endedEvents = new ArrayList<>();
        Date currentDate = new Date();

        endedEvents = events.stream().filter(
                event -> event.getEndDate().before(currentDate)
        ).collect(Collectors.toList());

        return endedEvents;
    }

    /**
     * Gets a list of events that are currently ongoing (are in progress).
     *
     * @return The list of ongoing events.
     */
    public List<Event> getOngoingEvents() {
        List<Event> ongoingEvents = new ArrayList<>();
        Date currentDate = new Date();

        ongoingEvents = events.stream().filter(
                event -> event.getStartDate().before(currentDate) && event.getEndDate().after(currentDate)
        ).collect(Collectors.toList());

        return ongoingEvents;
    }

    /**
     * Returns the maximum index of events in the collection. If the collection
     * is empty, it returns a default index of 1. The index is determined based
     * on the maximum event ID present in the collection.
     *
     * @return The maximum index of events incremented by 1, or
     * Integer.MIN_VALUE + 1 if the collection is empty.
     */
    public int getMaxIndex() {
        return events.stream()
                .mapToInt(Event::getId)
                .max()
                .orElse(0) + 1;
    }

    /**
     * Finds the index of an event with the specified ID in the collection.
     *
     * @param eventId The ID of the event to find in the collection.
     * @return The index of the event with the specified ID if found, or -1 if
     * not found.
     */
    private int findEventIndexById(int eventId) {
        for (int i = 0; i < this.events.size(); i++) {
            if (this.events.get(i).getId() == eventId) {
                return i;
            }
        }
        return -1;
    }

}
