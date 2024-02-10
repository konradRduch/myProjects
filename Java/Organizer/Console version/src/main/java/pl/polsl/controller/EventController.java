/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import pl.polsl.exceptions.IdNotExistsException;
import pl.polsl.exceptions.StartDateIsAfterEndDateException;
import pl.polsl.model.Event;
import pl.polsl.model.EventFileManager;
import pl.polsl.model.EventsModel;

import pl.polsl.view.EventView;
import pl.polsl.view.MenuView;

/**
 * Functional interface for generating new ID.
 */
@FunctionalInterface
interface Generate {
/**
     * Abstract method of the interface, which takes two integer values and returns the result of the operation.
     *
     * @param lineInFile Number of line in file.
     * @param offset Number by how much the new value will differ from the old one.
     * @return The result of the operation on the given integers.
     */
    int create(int lineInFile, int offset);
}

/**
 * Controller class for managing events in the application. Implements the
 * {@code State} interface for state handling.
 *
 * @author Konrad Rduch
 * @version f2
 * @since p1
 */
public class EventController implements State {

    private Scanner scanner;            //Scanner object for handling user input.
    private EventsModel eventsModel;    //Model responsible for managing the collection of events.
    private EventView eventView;        //View responsible for displaying events to the user.
    private String path;                //File path specifying the location to read and write event data.

    /**
     * Constructs an EventController with the specified Scanner and file path.
     * Initializes the EventsModel using data from the specified file.
     *
     * @param scanner The Scanner object for user input.
     * @param path The file path for storing event data.
     */
    public EventController(Scanner scanner, String path) {
        this.scanner = scanner;
        this.eventView = new EventView();
        this.path = path;

        EventFileManager readData = new EventFileManager(path);
        this.eventsModel = new EventsModel(readData.readFromFile());
    }

    /**
     * Adds a new event to the events model based on user input. Handles user
     * input for event details such as start date, end date, name, and
     * description. Validates date format and ensures the start date is not
     * after the end date. Generates an ID for the new event and saves the data
     * to the file. Handles exceptions related to invalid date formats and start
     * date being after the end date.
     */
    private void addEvent() {

        try {
            System.out.println("Enter the start date in the 'dd-MM-yyyy HH:mm': ");
            Scanner startDateScanner = new Scanner(System.in);
            String startDateInput = startDateScanner.nextLine();

            System.out.println("Enter the end date in the 'dd-MM-yyyy HH:mm': ");
            Scanner endDateScanner = new Scanner(System.in);
            String endDateInput = endDateScanner.nextLine();

            Date startDate = null;
            Date endDate = null;

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            try {
                startDate = dateFormat.parse(startDateInput);
                endDate = dateFormat.parse(endDateInput);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Failed to add the event.");
                return;
            }

            System.out.println("Enter the event name: ");
            Scanner eventNameScanner = new Scanner(System.in);
            String eventName = eventNameScanner.nextLine();

            System.out.println("Enter the event description: ");
            Scanner eventDescriptionScanner = new Scanner(System.in);
            String eventDescription = eventDescriptionScanner.nextLine();
            EventFileManager saveData = new EventFileManager(path);

            //lambda with defined interface 
            Generate newId = (lineInFile, offset) -> (lineInFile / 5) + offset;
            //Generating id for a new event
            int id = newId.create(saveData.countLines(), 1);

            Event event = new Event(id, eventName, startDate, endDate, eventDescription);
            eventsModel.addEvent(event);

            saveData.writeToFile(eventsModel.getEvents());

        } catch (StartDateIsAfterEndDateException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    /**
     * Updates the view to display the content of the events model. Invoked when
     * the user chooses to view events in the menu.
     */
    private void updateEventView() {
        eventView.showContent(eventsModel.getEvents());
    }

    /**
     * Edits an existing event based on user input. Handles user input for the
     * ID of the event to be edited and new details for the event. Validates
     * date format and ensures the start date is not after the end date. Saves
     * the edited event data to the file. Handles exceptions related to invalid
     * date formats, non-integer input, ID not existing, and start date being
     * after the end date.
     */
    private void editEvent() {

        try {
            System.out.println("Enter the event ID you want to edit: ");
            Scanner idScanner = new Scanner(System.in);
            int idInput = idScanner.nextInt();

            System.out.println("Enter the start date in the 'dd-MM-yyyy HH:mm' format: ");
            Scanner startDateScanner = new Scanner(System.in);
            String startDateInput = startDateScanner.nextLine();

            System.out.println("Enter the end date in the 'dd-MM-yyyy HH:mm' format: ");
            Scanner endDateScanner = new Scanner(System.in);
            String endDateInput = endDateScanner.nextLine();

            Date startDate = null;
            Date endDate = null;

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            try {
                startDate = dateFormat.parse(startDateInput);
                endDate = dateFormat.parse(endDateInput);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use 'dd-MM-yyyy HH:mm'.");
                return;
            }

            System.out.println("Enter the event name: ");
            Scanner eventNameScanner = new Scanner(System.in);
            String eventName = eventNameScanner.nextLine();

            System.out.println("Enter the event description: ");
            Scanner eventDescriptionScanner = new Scanner(System.in);
            String eventDescription = eventDescriptionScanner.nextLine();

            Event event = new Event(idInput, eventName, startDate, endDate, eventDescription);

            this.eventsModel.editEvent(event);

            EventFileManager saveData = new EventFileManager(path);
            saveData.writeToFile(eventsModel.getEvents());

        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: The entered value is not an integer.");
        } catch (IdNotExistsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (StartDateIsAfterEndDateException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    /**
     * Deletes an event based on user input. Handles user input for the ID of
     * the event to be deleted. Deletes the event from the events model and
     * saves the updated data to the file. Handles exceptions related to the ID
     * not existing.
     */
    private void deleteEvent() {
        try {
            System.out.println("Enter the event ID you want to delete: ");
            Scanner idScanner = new Scanner(System.in);
            int idInput = idScanner.nextInt();
            this.eventsModel.deleteEvent(idInput);
            EventFileManager saveData = new EventFileManager(path);
            saveData.writeToFile(eventsModel.getEvents());

        } catch (IdNotExistsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Displays a summary of events, including ended events, incoming events,
     * and ongoing events. Invoked when the user chooses to view a summary in
     * the menu.
     */
    private void summaryEvent() {
        System.out.println("ENDED EVENTS (" + this.eventsModel.getEndedEvents().size() + ")");
        this.eventView.showContent(this.eventsModel.getEndedEvents());
        System.out.println("INCOMING EVENTS (" + this.eventsModel.getIncomingEvents().size() + ")");
        this.eventView.showContent(this.eventsModel.getIncomingEvents());
        System.out.println("ONGOING EVENTS (" + this.eventsModel.getOngoingEvents().size() + ")");
        this.eventView.showContent(this.eventsModel.getOngoingEvents());
    }

    /**
     * Handles the state of the EventController, allowing the user to perform
     * various actions related to events.
     */
    @Override
    public void handleState() {
        while (true) {
            MenuView menuView = new MenuView();
            menuView.displayEventMenu();

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //Adding an event
                    addEvent();
                    break;
                case 2:
                    //Displaying events
                    updateEventView();
                    break;
                case 3:
                    //Editing an event
                    editEvent();
                    break;
                case 4:
                    //Deleting an event
                    deleteEvent();
                    break;
                case 5:
                    //Event summary
                    summaryEvent();
                    break;
                case 6:
                    //Returning to the Main Menu
                    return;
                default:
                //Invalid choice
            }
        }
    }

}
