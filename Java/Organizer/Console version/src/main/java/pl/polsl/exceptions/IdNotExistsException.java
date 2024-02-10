/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.exceptions;

/**
 * Custom exception class indicating that an ID does not exist.
 * This exception is thrown when attempting to perform an operation on an entity with a non-existent ID.
 * 
 * @author Konrad Rduch
 * @version f2
 * @since p1
 */
public class IdNotExistsException extends Exception {
    
    /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param message The detail message explaining the reason for the exception.
     */
    public IdNotExistsException(String message) {
        super(message);
    }
}
