/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.controller;

import java.util.NoSuchElementException;
import java.util.Scanner;
import pl.polsl.view.MenuView;

/**
 * Controller class for managing the main menu of the application.
 * Implements the {@code State} interface for state handling.
 * 
 * @author Konrad Rduch
 * @version f2
 * @since p1
 */
public class MenuController implements State {

    private final Scanner scanner;  // Scanner for user input
    MenuView menuView;  // View class for displaying the menu

    private String tasksFilePath;
    private String eventsFilePath;
    
    /**
     * Constructs a {@code MenuController} with the specified {@code Scanner} for input.
     * 
     * @param scanner The {@code Scanner} object for user input.
     * @param tasksPath The file path for tasks.
     * @param eventsPath The file path for events.
     */
    public MenuController(Scanner scanner, String tasksPath, String eventsPath) {
        this.tasksFilePath = tasksPath;
        this.eventsFilePath = eventsPath;
        this.scanner = scanner;
        this.menuView = new MenuView();
    }

    /**
     * Handles the state of the main menu, allowing the user to navigate to different sections.
     */
    @Override
    public void handleState() {
      
        while (true) {
            try {
                menuView.displayMainMenu();

                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Clear the newline character

                    switch (choice) {
                        case 1:
                            // Handling tasks
                            TaskController taskState = new TaskController(scanner, tasksFilePath);
                            taskState.handleState();
                            break;
                        case 2:
                            // Handling events
                            EventController eventState = new EventController(scanner, eventsFilePath);
                            eventState.handleState();
                            break;
                        case 3:
                            System.out.println("Exiting the application.");
                            return;
                        default:
                            System.out.println("Invalid choice.");
                    }
                } else {
                    System.out.println("Error: Please enter a valid number.");
                    scanner.nextLine(); // Clear the input buffer
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }
}
