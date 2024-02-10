/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.controller;

import java.util.Scanner;
import pl.polsl.exceptions.IdNotExistsException;
import pl.polsl.model.Task;
import pl.polsl.model.TaskFileManager;
import pl.polsl.model.TasksModel;
import pl.polsl.view.MenuView;

import pl.polsl.view.TaskView;

/**
 * Controller class for managing tasks, handling user input, and interacting
 * with the tasks model and view. This class implements the State interface.
 *
 * @author Konrad Rduch
 * @version f2
 * @since p1
 */
public class TaskController implements State {

    private Scanner scanner;        //Scanner for reading user input.
    private TasksModel tasksModel;  //Model for managing tasks.
    private TaskView taskView;      //View for displaying task-related content.
    private String path;            // File path for storing and retrieving task data.

    /**
     * Constructs a TaskController with the specified scanner and file path.
     * Initializes the tasks model and view based on file data.
     *
     * @param scanner The Scanner object for user input.
     * @param path The file path for task data.
     */
    public TaskController(Scanner scanner, String path) {
        this.scanner = scanner;
        this.tasksModel = new TasksModel();
        this.taskView = new TaskView();
        this.path = path;

        TaskFileManager readData = new TaskFileManager(path);
        this.tasksModel = new TasksModel(readData.readFromFile());
    }

    /**
     * Adds a new task to the tasks model based on user input. Prompts the user
     * for task description, creates a new Task object, and adds it to the
     * model. Saves the updated tasks to the file.
     */
    private void addTask() {
        try {
            int id = (tasksModel.getTasks().size()) + 1;

            System.out.println("Enter task description:");
            Scanner descriptionScanner = new Scanner(System.in);
            String description = descriptionScanner.nextLine();

            // Default value for 'done' is set to false for a new task
            Task task = new Task(id, description, false);
            tasksModel.addTask(task);

            TaskFileManager saveData = new TaskFileManager(path);
            saveData.writeToFile(tasksModel.getTasks());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Updates and displays the task view based on the current tasks in the
     * tasks model.
     */
    private void updateTaskView() {
        taskView.showContent(tasksModel.getTasks());
    }

    /**
     * Edits an existing task based on user input for task ID and new
     * description. Saves the updated tasks to the file.
     */
    private void editTask() {
        try {
            System.out.println("Enter the ID of the task you want to edit:");
            Scanner idScanner = new Scanner(System.in);
            int idInput = idScanner.nextInt(); // Consume the newline character

            System.out.println("Enter the new description for the task:");
            String newDescription = scanner.nextLine();

            Task editedTask = new Task(idInput, newDescription, false);

            this.tasksModel.editTask(editedTask);

            TaskFileManager saveData = new TaskFileManager(path);
            saveData.writeToFile(tasksModel.getTasks());

        } catch (java.util.InputMismatchException e) {
            System.out.println("Entered value is not an integer.");
        } catch (IdNotExistsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Deletes an existing task based on user input for task ID. Saves the
     * updated tasks to the file.
     */
    private void deleteTask() {
        try {
            System.out.println("Enter the ID of the task you want to delete:");
            Scanner idScanner = new Scanner(System.in);
            int idInput = idScanner.nextInt(); // Consume the newline character

            tasksModel.deleteTask(idInput);

            TaskFileManager saveData = new TaskFileManager(path);
            saveData.writeToFile(tasksModel.getTasks());

        } catch (java.util.InputMismatchException e) {
            System.out.println("Entered value is not an integer.");
        } catch (IdNotExistsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Toggles the status (done/undone) of an existing task based on user input
     * for task ID. Saves the updated tasks to the file.
     */
    private void toggleTaskStatus() {
        try {
            System.out.println("Enter the ID of the task you want to toggle:");
            Scanner idScanner = new Scanner(System.in);
            int idInput = idScanner.nextInt(); // Consume the newline character

            tasksModel.toggleTaskStatus(idInput);

            TaskFileManager saveData = new TaskFileManager(path);
            saveData.writeToFile(tasksModel.getTasks());

        } catch (java.util.InputMismatchException e) {
            System.out.println("Entered value is not an integer.");
        } catch (IdNotExistsException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    /**
     * Displays a summary of completed and uncompleted tasks.
     */
    private void summaryTask() {

        System.out.println("COMPLETED TASKS (" + this.tasksModel.filterTasksByStatus(true).size() + ")");
        this.taskView.showContent(this.tasksModel.filterTasksByStatus(true));
        System.out.println("UNCOMPLETED TASKS (" + this.tasksModel.filterTasksByStatus(false).size() + ")");
        this.taskView.showContent(this.tasksModel.filterTasksByStatus(false));

    }

    /**
     * Displays the task menu and handles user input for task-related
     * operations. Uses a loop to continuously prompt the user until they choose
     * to go back to the selection menu.
     */
    @Override
    public void handleState() {
        while (true) {
            MenuView menuView = new MenuView();
            menuView.displayTaskMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    //Adding a task
                    addTask();
                    break;
                case 2:
                    //Displaying tasks
                    updateTaskView();
                    break;
                case 3:
                   //Editing a task
                    editTask();
                    break;
                case 4:
                    //Deleting a task
                    deleteTask();
                    break;
                case 5:
                    //Toggling task status
                    toggleTaskStatus();
                    break;
                case 6:
                   //Summary of tasks
                    summaryTask();
                    break;
                case 7:
                    //Back to the Selection Menu
                    return;
                default:
                    //Invalid choice
            }
        }
    }
}
