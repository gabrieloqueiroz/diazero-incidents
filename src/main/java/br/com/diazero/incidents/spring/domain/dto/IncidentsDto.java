package br.com.diazero.incidents.spring.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncidentsDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
