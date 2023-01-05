package br.com.diazero.incidents.spring.exception.handler;

import br.com.diazero.incidents.spring.exception.BadRequestExceptionDetails;
import br.com.diazero.incidents.spring.exception.BusinessRuleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestExcpetion(BusinessRuleException businessException){
        return new ResponseEntity<>(
                BadRequestExceptionDetails
                        .builder()
                        .timestamp(LocalDateTime.now())
                        .title("REGISTER NOT FOUND, CHECK THE DOCUMENTATION")
                        .details(businessException.getMessage())
                        .build(), HttpStatus.OK);
    }
}
