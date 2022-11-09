package bo.edu.ucb.barkibu.util;

import org.springframework.http.HttpStatus;

public class BarkibuException extends RuntimeException {
    private String statusCode;
    private HttpStatus httpStatus;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public BarkibuException(String message) {
        super(message);
    }

    public BarkibuException(String message, Throwable cause) {
        super(message, cause);
    }

    public BarkibuException(String message, String statusCode, HttpStatus httpStatus) {
        super(message);
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
    }
}
