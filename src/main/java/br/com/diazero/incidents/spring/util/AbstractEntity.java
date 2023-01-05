package br.com.diazero.incidents.spring.util;

import br.com.diazero.incidents.spring.domain.dto.IncidentsDetailsDto;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDto;
import br.com.diazero.incidents.spring.domain.entity.Incidents;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbstractEntity {

    private static ModelMapper mapper;

    @Autowired
    public AbstractEntity(ModelMapper mapper) {
        AbstractEntity.mapper = mapper;
    }

    public static IncidentsDto toIncidentDto(Incidents incidents){
        return mapper.map(incidents, IncidentsDto.class);
    }

    public static IncidentsDetailsDto toIncidentDetailsDto(Incidents incidents){
        return mapper.map(incidents, IncidentsDetailsDto.class);
    }
}
