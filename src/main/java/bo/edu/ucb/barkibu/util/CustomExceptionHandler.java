package bo.edu.ucb.barkibu.util;

import bo.edu.ucb.barkibu.dto.ResponseDto;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<Object> handleInvalidJson(JsonParseException e) {
        return ResponseEntity.badRequest().body(new ResponseDto<>(null, "SCTY-1000", "Invalid data"));
    }
}
