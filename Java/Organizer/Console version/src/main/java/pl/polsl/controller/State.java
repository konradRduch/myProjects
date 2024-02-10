/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package pl.polsl.controller;

/**
 * This interface represents the state in a state machine.
 * It declares a method {@code handleState()} that must be implemented by concrete state classes.
 *
 * @author Konrad Rduch
 * @version f2
 * @since p1
 */
public interface State {

    /**
     * This method is responsible for handling the behavior associated with the current state.
     * Concrete classes implementing this interface should provide their specific implementation.
     */
    void handleState();
}
