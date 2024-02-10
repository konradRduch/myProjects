/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package pl.polsl.konradrduch;

import java.util.Scanner;
import pl.polsl.controller.MenuController;

/**
 * Main class for the Organizer application.
 *
 * @author Konrad Rduch
 * @version f2
 * @since p1
 */
public class Organizer {

    /**
     * The main method of the application.
     *
     * @param args Command line arguments. If provided, the first argument is
     * the task file path, and the second argument is the event file path. If no
     * command line arguments are provided, the application uses default folders
     * for tasks and events.
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Check if command line arguments are provided
        if (args.length == 2) {
            // Pass command line arguments to the MenuController
            MenuController mainMenuState = new MenuController(scanner, args[0], args[1]);
            mainMenuState.handleState();
        } else {
            // If no command line arguments, use default folders
            MenuController mainMenuState = new MenuController(scanner, "task.txt", "event.txt");
            mainMenuState.handleState();
        }
       
    }
}
