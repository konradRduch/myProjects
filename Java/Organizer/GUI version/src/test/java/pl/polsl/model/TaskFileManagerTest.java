/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


/**
 *
 * @author Konrad Rduch
 */
public class TaskFileManagerTest {
    
    public TaskFileManagerTest() {
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
    @CsvSource({"1, 'Eating breakfast', true", "2, 'Shopping with friends', false", "3, 'Working on tasks', true"})
    public void testReadFromFile(int taskId, String description, boolean done) throws IOException {
        // Arrange
        String filePath = "testTasksRead.txt";
        createTestFile(filePath, taskId, description, done);
        TaskFileManager taskFileManager = new TaskFileManager(filePath);

        // Act
        List<Task> tasks = taskFileManager.readFromFile();

        // Assert
        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertEquals(taskId, task.getId());
        assertEquals(description, task.getDescription());
        assertEquals(done, task.isDone());

        // Clean up: Delete the test file
        deleteTestFile(filePath);
    }

     @ParameterizedTest
    @CsvSource({
            "1, 'Eating lunch', false, 3",
            "2, 'Shopping', true, 3"
    })
    public void testWriteToFile(int taskId, String description, boolean done, int expectedLines) throws IOException {
        // Arrange
        String filePath = "testTasksWrite.txt";
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(taskId, description, done));
        TaskFileManager taskFileManager = new TaskFileManager(filePath);

        // Act
        taskFileManager.writeToFile(tasks);

        // Assert
        // Verify the number of lines in the file, each task takes 3 lines
        assertEquals(expectedLines, taskFileManager.countLines());

        // Clean up: Delete the test file
        deleteTestFile(filePath);

        // Act & Assert for null-check
        assertThrows(NullPointerException.class, () -> taskFileManager.writeToFile(null));

        // Clean up: Delete the test file if created (should not be created in this case)
        deleteTestFile(filePath);
    }

    private void createTestFile(String filePath, int taskId, String description, boolean done) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(taskId + "\n");
            writer.write(description + "\n");
            writer.write(done + "\n");
        }
    }
     private void deleteTestFile(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

    @ParameterizedTest
    @CsvSource({
            "1, 'Eating breakfast', true, 3",
            "2, 'Shopping with friends', false, 3",
            "3, 'Working on tasks', true, 3",
            "4, 'Coding', false, 3",
            "5, 'Reading a book', true, 3"
    })
    public void testCountLines(int taskId, String description, boolean done, int expectedLines) throws IOException {
        // Arrange
        String filePath = "testCountLines.txt";
        createTestFile(filePath, taskId, description, done);
        TaskFileManager taskFileManager = new TaskFileManager(filePath);

        // Act
        int lineCount = taskFileManager.countLines();

        // Assert
        assertEquals(expectedLines, lineCount, "Case for taskId: " + taskId);

        // Clean up: Delete the test file
        deleteTestFile(filePath);
    }


}
