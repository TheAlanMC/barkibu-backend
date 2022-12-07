package bo.edu.ucb.barkibu.util;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class HttpMessageUtil {
    private String message;
    private HttpStatus httpStatus;
    public static Map<String, HttpMessageUtil> httpMessageUtilMap = errorHashMap();

    public HttpMessageUtil(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "HttpMessageUtil{" +
                "message='" + message + '\'' +
                ", httpStatus='" + httpStatus + '\'' +
                '}';
    }

    // HashMap con todos los mensajes de error
    public static Map<String, HttpMessageUtil> errorHashMap() {
        Map<String, HttpMessageUtil> errorHashMap = new HashMap<>();
        // Errores de tipo bad request (400)
        errorHashMap.put("SCTY-1000", new HttpMessageUtil("Invalid data", HttpStatus.BAD_REQUEST));
        errorHashMap.put("SCTY-1001", new HttpMessageUtil("At least one field is empty", HttpStatus.BAD_REQUEST));
        errorHashMap.put("SCTY-1002", new HttpMessageUtil("Usernamae already exists", HttpStatus.BAD_REQUEST));
        errorHashMap.put("SCTY-1003", new HttpMessageUtil("Email already exists", HttpStatus.BAD_REQUEST));
        errorHashMap.put("SCTY-1004", new HttpMessageUtil("Email format is not valid", HttpStatus.BAD_REQUEST));
        errorHashMap.put("SCTY-1005", new HttpMessageUtil("Code is not valid", HttpStatus.BAD_REQUEST));
        errorHashMap.put("SCTY-1006", new HttpMessageUtil("Code has expired", HttpStatus.BAD_REQUEST));
        errorHashMap.put("SCTY-1007",
                new HttpMessageUtil("New password and confirmation must be equals", HttpStatus.BAD_REQUEST));
        errorHashMap.put("SCTY-1008", new HttpMessageUtil("Date must be before today", HttpStatus.BAD_REQUEST));
        errorHashMap.put("SCTY-1009", new HttpMessageUtil("Veterinary already exists", HttpStatus.BAD_REQUEST));
        errorHashMap.put("SCTY-1010", new HttpMessageUtil("Current password is incorrect", HttpStatus.BAD_REQUEST));
        errorHashMap.put("SCTY-1011",
                new HttpMessageUtil("New password must be different to current password", HttpStatus.BAD_REQUEST));
        errorHashMap.put("SCTY-1012", new HttpMessageUtil("Question already answered", HttpStatus.BAD_REQUEST));
        errorHashMap.put("SCTY-1013", new HttpMessageUtil("Answer already liked", HttpStatus.BAD_REQUEST));
        errorHashMap.put("SCTY-1014", new HttpMessageUtil("Username cannot have spaces", HttpStatus.BAD_REQUEST));
        errorHashMap.put("SCTY-1015", new HttpMessageUtil("Date must be after today", HttpStatus.BAD_REQUEST));

        // Errores de tipo unauthorized (401) -- no autehnticado
        errorHashMap.put("SCTY-2000", new HttpMessageUtil("Invalid credentials", HttpStatus.UNAUTHORIZED));
        errorHashMap.put("SCTY-2001", new HttpMessageUtil("Invalid token", HttpStatus.UNAUTHORIZED));
        errorHashMap.put("SCTY-2002", new HttpMessageUtil("Expired token", HttpStatus.UNAUTHORIZED));
        errorHashMap.put("SCTY-2003", new HttpMessageUtil("No token provided", HttpStatus.UNAUTHORIZED));
        // Errores de tipo forbidden (403) -- no autorizado
        errorHashMap.put("SCTY-3000",
                new HttpMessageUtil("User is not allowed to access this resource", HttpStatus.FORBIDDEN));
        errorHashMap.put("SCTY-3001",
                new HttpMessageUtil("User has been blocked, please try again later", HttpStatus.FORBIDDEN));
        // Errores de tipo not found (404) -- no encontrado
        errorHashMap.put("SCTY-4000", new HttpMessageUtil("User not found", HttpStatus.NOT_FOUND));
        errorHashMap.put("SCTY-4001", new HttpMessageUtil("City not found", HttpStatus.NOT_FOUND));
        errorHashMap.put("SCTY-4002", new HttpMessageUtil("State not found", HttpStatus.NOT_FOUND));
        errorHashMap.put("SCTY-4003", new HttpMessageUtil("Country not found", HttpStatus.NOT_FOUND));
        errorHashMap.put("SCTY-4004", new HttpMessageUtil("Veterinary not found", HttpStatus.NOT_FOUND));
        errorHashMap.put("SCTY-4005", new HttpMessageUtil("Question not found", HttpStatus.NOT_FOUND));
        errorHashMap.put("SCTY-4006", new HttpMessageUtil("Question category not found", HttpStatus.NOT_FOUND));
        errorHashMap.put("SCTY-4007", new HttpMessageUtil("Specie not found", HttpStatus.NOT_FOUND));
        errorHashMap.put("SCTY-4008", new HttpMessageUtil("Pet not found", HttpStatus.NOT_FOUND));
        errorHashMap.put("SCTY-4009", new HttpMessageUtil("Answer not found", HttpStatus.NOT_FOUND));
        errorHashMap.put("SCTY-4010", new HttpMessageUtil("Recovery Acount code not found", HttpStatus.NOT_FOUND));
        errorHashMap.put("SCTY-4011", new HttpMessageUtil("No more questions available", HttpStatus.NOT_FOUND));
        errorHashMap.put("SCTY-4012", new HttpMessageUtil("No more answers available", HttpStatus.NOT_FOUND));
        errorHashMap.put("SCTY-4013", new HttpMessageUtil("Treatment not found", HttpStatus.NOT_FOUND));
        // Errores de tipo server error (500) -- error interno del servidor
        errorHashMap.put("SCTY-5000", new HttpMessageUtil("Error generating token", HttpStatus.INTERNAL_SERVER_ERROR));
        return errorHashMap;
    }
}
