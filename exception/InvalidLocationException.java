package exception;

/**
 * Custom exception to handle scenarios related to invalid locations.
 * This exception can be used to indicate issues like out-of-bounds coordinates,
 * inaccessible areas, or any other anomalies associated with locations in the system.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public class InvalidLocationException extends RuntimeException {

    /**
     * Constructs a new InvalidLocationException with the specified detail message.
     * 
     * @param message The detail message explaining the reason for the exception.
     */
    public InvalidLocationException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidLocationException with the specified cause.
     * 
     * @param cause The root cause of this exception.
     */
    public InvalidLocationException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new InvalidLocationException with the specified detail message and cause.
     * 
     * @param message The detail message explaining the reason for the exception.
     * @param cause   The root cause of this exception.
     */
    public InvalidLocationException(String message, Throwable cause) {
        super(message, cause);
    }
}

