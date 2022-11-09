package bo.edu.ucb.barkibu.util;

public class BarkibuException extends RuntimeException {
    public BarkibuException(String message) {
        super(message);
    }

    public BarkibuException(String message, Throwable cause) {
        super(message, cause);
    }
}
