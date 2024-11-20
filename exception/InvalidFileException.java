package exception;

/**
 * Custom exception to handle scenarios related to invalid files.
 * This can be used to indicate issues like incorrect file format, missing required data,
 * or any other file-related anomalies in the system.
 * 
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
public class InvalidFileException extends RuntimeException {

    /**
     * Constructs a new InvalidFileException with the specified detail message.
     * 
     * @param message The detail message explaining the reason for the exception.
     */
    public InvalidFileException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidFileException with the specified cause.
     * 
     * @param cause The root cause of this exception.
     */
    public InvalidFileException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new InvalidFileException with the specified detail message and cause.
     * 
     * @param message The detail message explaining the reason for the exception.
     * @param cause   The root cause of this exception.
     */
    public InvalidFileException(String message, Throwable cause) {
        super(message, cause);
    }
}

