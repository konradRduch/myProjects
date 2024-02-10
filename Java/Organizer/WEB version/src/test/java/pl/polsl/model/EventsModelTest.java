/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.model;

import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.exceptions.IdNotExistsException;
import pl.polsl.exceptions.StartDateIsAfterEndDateException;

/**
 *
 * @author Konrad Rduch
 */
public class EventsModelTest {

    private EventsModel eventsModel;

    @BeforeEach
    public void setUp() {
        eventsModel = EventsModel.getInstance();
    }

    @AfterEach
    public void tearDown() {
        eventsModel.getEvents().clear();
    }

    
    @ParameterizedTest
    @CsvSource({
            "1, 'Event 1', 'Description 1', '2023-01-01', '2023-01-02'", // Test case to add an event
            "2, 'Event 2', 'Description 2', '2023-02-01', '2023-02-02'" // Test case to add another event
    })
    public void testAddEvent(int eventId, String eventTitle, String eventDescription,
                              String startDateStr, String endDateStr) throws StartDateIsAfterEndDateException {
        // Arrange
        // No need to create a new instance, as EventsModel is a singleton

        // Act
        Date startDate = parseDate(startDateStr);
        Date endDate = parseDate(endDateStr);

        Event event = new Event(eventId, eventTitle, startDate, endDate, eventDescription);
        eventsModel.addEvent(event);

        // Assert
        assertTrue(eventsModel.getEvents().contains(event));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 'Event 1', 'Description 1', '2023-01-01', '2023-01-02', 'Edited Event 1', 'Edited Description 1', '2023-02-01', '2023-02-02'" // Test case to edit an event
    })
    public void testEditEvent(int eventId, String existingEventTitle, String existingEventDescription,
                               String existingStartDateStr, String existingEndDateStr,
                               String newEventTitle, String newEventDescription,
                               String newStartDateStr, String newEndDateStr) throws IdNotExistsException, StartDateIsAfterEndDateException {
        // Arrange
        // No need to create a new instance, as EventsModel is a singleton
        Date existingStartDate = parseDate(existingStartDateStr);
        Date existingEndDate = parseDate(existingEndDateStr);

        Event existingEvent = new Event(eventId, existingEventTitle, existingStartDate, existingEndDate, existingEventDescription);
        eventsModel.addEvent(existingEvent);

        // Act
        Date newStartDate = parseDate(newStartDateStr);
        Date newEndDate = parseDate(newEndDateStr);

        Event editedEvent = new Event(eventId, newEventTitle, newStartDate, newEndDate, newEventDescription);
        eventsModel.editEvent(editedEvent);

        // Assert
        Event resultEvent = eventsModel.getEvents().get(0);
        assertEquals(newEventTitle, resultEvent.getTitle());
        assertEquals(newEventDescription, resultEvent.getDescription());
        assertEquals(newStartDate, resultEvent.getStartDate());
        assertEquals(newEndDate, resultEvent.getEndDate());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 'Event 1', 'Description 1', '2023-12-01', '2023-12-10'", // Test case to delete existing event
    })
    public void testDeleteEvent(int eventId, String eventTitle, String eventDescription,
                                 String startDateStr, String endDateStr) throws StartDateIsAfterEndDateException {
        // Arrange
        // No need to create a new instance, as EventsModel is a singleton
        Date startDate = parseDate(startDateStr);
        Date endDate = parseDate(endDateStr);

        Event existingEvent = new Event(eventId, eventTitle, startDate, endDate, eventDescription);
        eventsModel.addEvent(existingEvent);

        // Act
        try {
            eventsModel.deleteEvent(eventId);
        } catch (IdNotExistsException e) {
            // Ignore exception for this test
        }

        // Assert
        assertEquals(0, eventsModel.getEvents().size());
    }

    // Helper method to parse Date strings
    private Date parseDate(String dateStr) {
        // Implement date parsing logic based on your requirements
        // For simplicity, returning a new Date() in this example
        return new Date();
    }

}
