/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.view;

import java.text.SimpleDateFormat;
import java.util.List;
import pl.polsl.model.Event;

/**
 * View class responsible for displaying information about events.
 * Implements the ContentDisplayView interface for showing event-related content.
 * 
 * @author Konrad Rduch
 * @version f2
 * @since p1
 */
public class EventView implements ContentDisplayView<Event> {

    /**
     * Displays information about a list of events, including their IDs, titles, start and end dates, descriptions.
     * 
     * @param events The list of events to be displayed.
     */
    public void showContent(List<Event> events) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        for (Event event : events) {
            System.out.println("Id: " + event.getId());
            System.out.println("Title: " + event.getTitle());
            System.out.println("Start: " + dateFormat.format(event.getStartDate()));
            System.out.println("End: " + dateFormat.format(event.getEndDate()));
            System.out.println("Description: " + event.getDescription());
            System.out.println("---------------------------------------------");
        }
    }

}
