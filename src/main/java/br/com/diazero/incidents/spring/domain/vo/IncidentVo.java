package br.com.diazero.incidents.spring.domain.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncidentVo {
    private String name;
    private String description;
}
