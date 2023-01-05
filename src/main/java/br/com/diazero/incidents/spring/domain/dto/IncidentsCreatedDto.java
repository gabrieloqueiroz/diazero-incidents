package br.com.diazero.incidents.spring.domain.dto;

import br.com.diazero.incidents.spring.domain.enuns.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncidentsCreatedDto {

    private Long id;
    private String name;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
}
