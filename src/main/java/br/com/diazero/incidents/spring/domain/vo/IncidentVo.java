package br.com.diazero.incidents.spring.domain.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class IncidentVo {
    @NotEmpty
    @NotNull
    @Length(min = 10)
    private String name;

    @NotEmpty
    @NotNull
    @Length(min = 20)
    private String description;
}
