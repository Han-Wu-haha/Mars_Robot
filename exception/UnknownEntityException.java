package exception;

/**
 * Custom exception to handle scenarios where an entity is not recognized or not found.
 * This exception can be triggered when trying to access or operate on an entity
 * that doesn't exist or isn't registered within the system's known entities.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public class UnknownEntityException extends RuntimeException {

    /**
     * Constructs a new UnknownEntityException with the specified detail message.
     * 
     * @param message The detail message explaining the reason for the exception.
     */
    public UnknownEntityException(String message) {
        super(message);
    }

    /**
     * Constructs a new UnknownEntityException with the specified cause.
     * 
     * @param cause The root cause of this exception.
     */
    public UnknownEntityException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new UnknownEntityException with both a specified detail message and cause.
     * 
     * @param message The detail message explaining the reason for the exception.
     * @param cause   The root cause of this exception.
     */
    public UnknownEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}

