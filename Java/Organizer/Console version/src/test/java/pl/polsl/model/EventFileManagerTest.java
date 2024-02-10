/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Konrad Rduch
 */
public class EventFileManagerTest {
    
    public EventFileManagerTest() {
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
    @CsvSource({
            "1, 'Event 1', '203-124-01 08:00:00', '2023-12-01 10:0:00', 'Description 1', 5",
            "2, 'Event 2', '2023-12-02 12:00:00', '2023-12-02 15:00:00', 'Description 2', 5",
            "3, 'Event 3', '2023-12-03 18:00:00', '2023-12-03 20:00:00', 'Description 3', 5"
    })
    public void testReadFromFile(int id, String title, String startDate, String endDate, String description, int expectedLines) throws IOException, ParseException {
        // Arrange
        String filePath = "testEventsRead.txt";
        createTestFile(filePath, id, title, startDate, endDate, description);
        EventFileManager eventFileManager = new EventFileManager(filePath);

        // Act
        List<Event> events = eventFileManager.readFromFile();

        // Assert
        assertEquals(1, events.size());
        Event event = events.get(0);
        assertEquals(id, event.getId());
        assertEquals(title, event.getTitle());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate), event.getStartDate());
        assertEquals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate), event.getEndDate());
        assertEquals(description, event.getDescription());

        // Clean up: Delete the test file
        deleteTestFile(filePath);
    }

     @ParameterizedTest
    @CsvSource({
            "1, 'Event 1', '2023-12-01 08:00:00', '2023-12-01 10:00:00', 'Description 1', 5",
            "2, 'Event 2', '2023-12-02 12:00:00', '2023-12-02 15:00:00', 'Description 2', 5",
            "3, 'Event 3', '2023-12-03 18:00:00', '2023-12-03 20:00:00', 'Description 3', 5"
    })
    public void testWriteToFileAndNullCheck(int id, String title, String startDate, String endDate, String description, int expectedLines) throws IOException, ParseException {
        // Arrange
        String filePath = "testEventsWrite.txt";
        List<Event> events = new ArrayList<>();
        events.add(new Event(id, title, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate), description));
        EventFileManager eventFileManager = new EventFileManager(filePath);

        // Act
        eventFileManager.writeToFile(events);

        // Assert
        // Verify the number of lines in the file, each event takes 5 lines
        assertEquals(expectedLines, eventFileManager.countLines());

        // Act & Assert for null-check
        assertThrows(NullPointerException.class, () -> eventFileManager.writeToFile(null));

        // Clean up: Delete the test file
        deleteTestFile(filePath);
    }

    private void createTestFile(String filePath, int id, String title, String startDate, String endDate, String description) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(id + "\n");
            writer.write(title + "\n");
            writer.write(startDate + "\n");
            writer.write(endDate + "\n");
            writer.write(description + "\n");
        }
    }

    private void deleteTestFile(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

    @Test
    public void testCountLines() {
        // TODO: Implement test cases for countLines method
    }
}
