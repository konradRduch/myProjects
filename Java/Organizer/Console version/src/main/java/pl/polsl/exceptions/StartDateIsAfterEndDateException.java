/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.exceptions;

/**
 * Custom exception class indicating that the start date of an event is after its end date.
 * This exception is thrown when attempting to create or edit an event with an invalid date range.
 * 
 * @author Konrad Rduch
 * @version f2
 * @since p1
 */
public class StartDateIsAfterEndDateException extends Exception {
    
    /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param message The detail message explaining the reason for the exception.
     */
    public StartDateIsAfterEndDateException(String message) {
        super(message);
    }
}

