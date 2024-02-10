/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Manages reading and writing events to/from a file.
 *
 * @author Konrad Rduch
 * @version f4
 * @since p1
 */
public class EventFileManager {

    private String path; // The file path for reading/writing events

    /**
     * Constructs an EventFileManager with the specified file path.
     *
     * @param path The file path for reading/writing events.
     */
    public EventFileManager(String path) {
        this.path = path;
    }

    /**
     * Reads events from a file and returns a list of events.
     *
     * @return The list of events read from the file.
     */
    public List<Event> readFromFile() {
        List<Event> events = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(path))) {
            while (scanner.hasNext()) {
                int id = scanner.nextInt();
                scanner.nextLine(); // Move to the next line after reading int
                String title = scanner.nextLine();
                String startDateString = scanner.nextLine();
                String endDateString = scanner.nextLine();
                String description = scanner.nextLine();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date startDate = dateFormat.parse(startDateString);
                Date endDate = dateFormat.parse(endDateString);

                // Create Event object based on the read data and add it to the list
                Event event = new Event(id, title, startDate, endDate, description);
                events.add(event);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Error parsing date: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return events;
    }

    /**
     * Writes a list of events to a file.
     *
     * @param events The list of events to be written to the file.
     */
    public void writeToFile(List<Event> events) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // Save event data to the file
            for (Event event : events) {
                writer.println(event.getId());
                writer.println(event.getTitle());
                writer.println(dateFormat.format(event.getStartDate()));
                writer.println(dateFormat.format(event.getEndDate()));
                writer.println(event.getDescription());
            }
        } catch (IOException e) {
             JOptionPane.showMessageDialog(null, "Error while writing to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Counts the number of lines in the file.
     *
     * @return The number of lines in the file.
     */
    public int countLines() {
        int lineCount = 0;

        try (Scanner scanner = new Scanner(new FileReader(path))) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lineCount++;
            }
        } catch (FileNotFoundException e) {
             JOptionPane.showMessageDialog(null, "File not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
       
        }
        return lineCount;
    }
}
