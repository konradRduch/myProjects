/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package pl.polsl.konradrduch;

import pl.polsl.view.OrganizerGUI;
/**
 * Main class for the Organizer application.
 *
 * @author Konrad Rduch
 * @version f3
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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (args.length == 2) {
                    // Pass command line arguments 
                    new OrganizerGUI(args[0], args[1]).setVisible(true);
                } else {
                    // If no arguments, use default folders
                    new OrganizerGUI("task.txt", "event.txt").setVisible(true);
                }

            }
        });
    }
}
