package br.com.diazero.incidents.spring.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Incidents {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private LocalDateTime closeAt;
}
