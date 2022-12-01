package api.restapi.exception;

import api.restapi.domain.dto.ApiExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class, DataFormatException.class})
    protected ResponseEntity<ApiExceptionDTO> handleConflict(ResourceNotFoundException ex) {
        ApiExceptionDTO apiDto = new ApiExceptionDTO(ex.getMessage());
        return ResponseEntity.badRequest().body(apiDto);
    }
}