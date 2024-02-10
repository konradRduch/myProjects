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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for managing tasks by reading from and writing to a file.
 *
 * @author Konrad Rduch
 * @version f2
 * @since p1
 */
public class TaskFileManager {

    private String path; // The file path for reading/writing tasks

    /**
     * Constructs a TaskFileManager with the specified file path.
     *
     * @param path The file path for reading/writing tasks.
     */
    public TaskFileManager(String path) {
        this.path = path;
    }

    /**
     * Reads tasks from a file and returns a list of tasks.
     *
     * @return The list of tasks read from the file.
     */
    public List<Task> readFromFile() {
        List<Task> tasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(path))) {
            while (scanner.hasNext()) {
                int id = scanner.nextInt();
                scanner.nextLine(); // Move to the next line after reading int
                String description = scanner.nextLine();
                boolean done = Boolean.parseBoolean(scanner.nextLine());

                // Create Task object based on the read data and add it to the list
                Task task = new Task(id, description, done);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Writes a list of tasks to a file.
     *
     * @param tasks The list of tasks to be written to the file.
     */
    public void writeToFile(List<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            // Save task data to the file
            for (Task task : tasks) {
                writer.println(task.getId());
                writer.println(task.getDescription());
                writer.println(task.isDone());
            }
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getMessage());
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
            System.err.println("File not found: " + e.getMessage());
        }

        return lineCount;
    }
}
