package bo.edu.ucb.barkibu.util;

import bo.edu.ucb.barkibu.dto.ResponseDto;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static bo.edu.ucb.barkibu.util.HttpMessageUtil.httpMessageUtilMap;


@ControllerAdvice
public class CustomExceptionHandler {

    // Manejo de excepciones para el parseo de JSON
    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<Object> handleInvalidJson(JsonParseException e) {
        String statusCode = "SCTY-1000";
        return ResponseEntity.badRequest().body(new ResponseDto<>(null, statusCode,httpMessageUtilMap.get(statusCode).getMessage()));
    }
}
