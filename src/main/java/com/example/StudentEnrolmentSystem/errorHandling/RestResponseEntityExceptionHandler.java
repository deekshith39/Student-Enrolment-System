package com.example.StudentEnrolmentSystem.errorHandling;

import com.example.StudentEnrolmentSystem.model.response.BusinessErrorResponse;
import com.example.StudentEnrolmentSystem.model.response.GenericErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ValidationUnsuccessfulException.class)
    public ResponseEntity<BusinessErrorResponse> validationUnsuccessfulException(ValidationUnsuccessfulException exception, WebRequest request) {

        String errorMessage = messageSource.getMessage(exception.getCode(), null, null);
        BusinessErrorResponse message = new BusinessErrorResponse(Timestamp.from(Instant.now()), exception.getCode(), errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<GenericErrorResponse> dataBaseException(DataBaseException exception,
                                                                    WebRequest request) {
        GenericErrorResponse message = new GenericErrorResponse(Timestamp.from(Instant.now()), exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GenericErrorResponse> NotFoundException(NotFoundException exception,
                                                                   WebRequest request) {
        GenericErrorResponse message = new GenericErrorResponse(Timestamp.from(Instant.now()), exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
