package br.com.diazero.incidents.spring.domain.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
