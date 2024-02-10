/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.model;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.exceptions.IdNotExistsException;

/**
 *
 * @author Konrad Rduch
 */
public class TasksModelTest {

    public TasksModelTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @ParameterizedTest
    @CsvSource({"0, 'Eating breakfast', true", "1, 'Shopping with friends', false", "2147483647, '132312@#ads', true", "2, null, true"})
    public void testAddTask(int taskId, String taskDescription, boolean done) {
        // Arrange
        TasksModel tasksModel = new TasksModel();
        Task task = (taskDescription != null) ? new Task(taskId, taskDescription, done) : null;

        // Act
        tasksModel.addTask(task);

        // Assert
        if (task != null) {
            assertTrue(tasksModel.getTasks().contains(task));
        } else {
            assertEquals(0, tasksModel.getTasks().size()); // Sprawdzamy, czy lista zadań jest nadal pusta
        }
    }

    @ParameterizedTest
@CsvSource({
    "1, 'Task 1', false, 1, 'Edited Task 1', true" // Test case to edit existing task
   
})
public void testEditTask(int existingTaskId, String existingTaskDescription, boolean existingTaskDone,
        int taskIdToEdit, String newDescription, boolean newDoneStatus) {
    // Arrange
    TasksModel tasksModel = new TasksModel();
    Task existingTask = new Task(existingTaskId, existingTaskDescription, existingTaskDone);
    tasksModel.addTask(existingTask);

    // Act
    boolean editResult;
    try {
        Task editedTask = new Task(taskIdToEdit, newDescription, newDoneStatus);
        tasksModel.editTask(editedTask);
        editResult = true;
    } catch (IdNotExistsException e) {
        editResult = false;
    }

    // Assert
    assertEquals(existingTaskId == taskIdToEdit, editResult);

    // Additional Assert
    if (existingTaskId == taskIdToEdit) {
        // Task was edited successfully
        Task resultTask = tasksModel.getTasks().get(existingTaskId - 1);
        assertEquals(newDescription, resultTask.getDescription());
        assertEquals(newDoneStatus, resultTask.isDone());
    } else {
        // Task was not edited, ensure the list is unchanged
        assertEquals(existingTaskDescription, tasksModel.getTasks().get(existingTaskId - 1).getDescription());
        assertEquals(existingTaskDone, tasksModel.getTasks().get(existingTaskId - 1).isDone());
    }
}

    @ParameterizedTest
    @CsvSource({
        "1, 'Task 1', false, 1, true", // Test case to delete existing task
        "2, 'Task 2', true, 3, false" // Test case to delete non-existent task
    })
    public void testDeleteTask(int existingTaskId, String existingTaskDescription, boolean existingTaskDone,
            int taskIdToDelete, boolean expectedResult) {
        // Arrange
        TasksModel tasksModel = new TasksModel();
        Task existingTask = new Task(existingTaskId, existingTaskDescription, existingTaskDone);
        tasksModel.addTask(existingTask);

        // Act
        boolean deletionResult;
        try {
            tasksModel.deleteTask(taskIdToDelete);
            deletionResult = true;
        } catch (IdNotExistsException e) {
            deletionResult = false;
        }

        // Assert
        assertEquals(expectedResult, deletionResult);

        // Additional Assert
        if (expectedResult) {
            assertEquals(0, tasksModel.getTasks().size()); // Check that the list is empty after deletion
        } else {
            assertEquals(1, tasksModel.getTasks().size()); // Check that the list is unchanged
        }
    }

    @ParameterizedTest
    @CsvSource({"0, 'Eating breakfast', 1", "1, 'Shopping with friends', 0", "-1, '132312@#ads', 0"})
    public void testToggleTaskStatus(int taskId, String taskDescription, boolean initialStatus) throws IdNotExistsException {
        // Arrange
        TasksModel tasksModel = new TasksModel();
        Task task = new Task(taskId, taskDescription, initialStatus);
        tasksModel.addTask(task);

        // Act
        tasksModel.toggleTaskStatus(taskId);

        // Assert
        List<Task> filteredTasks = tasksModel.filterTasksByStatus(!initialStatus);
        assertTrue(filteredTasks.stream().anyMatch(t -> t.getId() == taskId && t.isDone() == !initialStatus));
    }

    @ParameterizedTest
    @CsvSource({
        "true, 2, 0",
        "false, 1, 1"
    })
    public void testFilterTasksByStatus(boolean statusToFilter, int expectedSize, int taskIdToCheck) {
        // Arrange
        TasksModel tasksModel = new TasksModel();
        Task task1 = new Task(0, "Task 1", true);
        Task task2 = new Task(1, "Task 2", false);
        Task task3 = new Task(2, "Task 3", true);
        tasksModel.addTask(task1);
        tasksModel.addTask(task2);
        tasksModel.addTask(task3);

        // Act
        List<Task> filteredTasks = tasksModel.filterTasksByStatus(statusToFilter);

        // Assert
        assertEquals(expectedSize, filteredTasks.size());
        assertTrue(filteredTasks.stream().anyMatch(t -> t.getId() == taskIdToCheck && t.isDone() == statusToFilter));
    }
}
