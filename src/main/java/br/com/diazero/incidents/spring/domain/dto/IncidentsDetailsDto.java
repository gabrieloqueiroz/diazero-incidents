package br.com.diazero.incidents.spring.domain.dto;

import br.com.diazero.incidents.spring.domain.enuns.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class IncidentsDetailsDto {

    private Long Id;
    private String name;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime closedAt;
    private List<String> comments;
}
