/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.view;

import java.util.List;
import pl.polsl.model.Task;

/**
 * View class responsible for displaying information about tasks. Implements the
 * ContentDisplayView interface for showing task-related content.
 *
 * @author Konrad Rduch
 * @version f2
 * @since p1
 */
public class TaskView implements ContentDisplayView<Task> {

    /**
     * Displays information about a list of tasks, including their IDs,
     * descriptions, and completion statuses.
     *
     * @param tasks The list of tasks to be displayed.
     */
    @Override
    public void showContent(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println("Id: " + task.getId());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Status: " + (task.isDone() ? "Completed" : "Uncompleted"));
            System.out.println("---------------------------------------------");
        }
    }

}
