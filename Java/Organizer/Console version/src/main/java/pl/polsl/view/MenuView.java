/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.view;

/**
 * View class responsible for displaying menus related to the application.
 *
 * @author Konrad Rduch
 * @version f2
 * @since p1
 */
public class MenuView {

    /**
     * Displays the main menu options.
     */
    public void displayMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Task");
        System.out.println("2. Event");
        System.out.println("3. Exit");
        System.out.println("----------------------------");
    }

    /**
     * Displays the menu options related to tasks.
     */
    public void displayTaskMenu() {
        System.out.println("Task:");
        System.out.println("1. Add task");
        System.out.println("2. Show all tasks");
        System.out.println("3. Edit task");
        System.out.println("4. Delete task");
        System.out.println("5. Toggle task status");
        System.out.println("6. Summary");
        System.out.println("7. Exit");
        System.out.println("----------------------------");
    }

    /**
     * Displays the menu options related to events.
     */
    public void displayEventMenu() {
        System.out.println("Event:");
        System.out.println("1. Add event");
        System.out.println("2. Show all events");
        System.out.println("3. Edit event");
        System.out.println("4. Delete event");
        System.out.println("5. Summary");
        System.out.println("6. Exit");
        System.out.println("----------------------------");
    }
}
