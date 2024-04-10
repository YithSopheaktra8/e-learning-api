package co.istad.elearningapi.exception;

import co.istad.elearningapi.base.BaseError;
import co.istad.elearningapi.base.BaseErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

@RestControllerAdvice
public class DatabaseException {


    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handleSqlException(SQLException ex){

        BaseError<String> baseError = new BaseError<>();
        baseError.setCode(HttpStatus.BAD_REQUEST.toString());
        baseError.setDescription(ex.getLocalizedMessage());

        BaseErrorResponse baseErrorResponse = BaseErrorResponse.builder()
                .baseError(baseError)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(baseErrorResponse);

    }

}
