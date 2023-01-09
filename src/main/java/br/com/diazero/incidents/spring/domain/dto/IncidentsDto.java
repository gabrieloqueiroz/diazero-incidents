package br.com.diazero.incidents.spring.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidentsDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
