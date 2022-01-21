
// Created for CS1331 at Georgia Tech
// By Allison Sawyer
// Spring 2019


/**
 * This class represents a custom exception that extends from RuntimeException
 * @author asawyer8
 * @version 1.0
*/
public class InvalidSearchStringException extends RuntimeException {
    /** Construct an exception */
    public InvalidSearchStringException() {
        super("New line characters are not allowed.");
    }
}
