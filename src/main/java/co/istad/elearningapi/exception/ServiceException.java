package co.istad.elearningapi.exception;

import co.istad.elearningapi.base.BaseError;
import co.istad.elearningapi.base.BaseErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ServiceException {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleServiceError(ResponseStatusException ex){

        BaseError<String> baseError = new BaseError<>();
        baseError.setCode(ex.getStatusCode().toString());
        baseError.setDescription(ex.getReason());

        BaseErrorResponse baseErrorResponse = BaseErrorResponse.builder()
                .baseError(baseError)
                .build();

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(baseErrorResponse);
    }

}
