package bo.edu.ucb.barkibu.util;

import org.springframework.http.HttpStatus;

import static bo.edu.ucb.barkibu.util.HttpMessageUtil.httpMessageUtilMap;


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

    public BarkibuException(String statusCode) {
        super(httpMessageUtilMap.get(statusCode).getMessage());
        this.statusCode = statusCode;
        this.httpStatus = httpMessageUtilMap.get(statusCode).getHttpStatus();
    }

}









