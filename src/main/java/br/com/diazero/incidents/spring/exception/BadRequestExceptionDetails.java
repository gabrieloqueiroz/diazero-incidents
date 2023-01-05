package br.com.diazero.incidents.spring.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExceptionDetails {

    private String title;
    private String details;
    private LocalDateTime timestamp;

}
