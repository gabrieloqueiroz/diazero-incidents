package br.com.diazero.incidents.spring.util;

import br.com.diazero.incidents.spring.domain.dto.IncidentsCreatedDto;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDetailsDto;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDto;
import br.com.diazero.incidents.spring.domain.entity.Incidents;
import br.com.diazero.incidents.spring.domain.vo.IncidentVo;
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

    public static IncidentsCreatedDto toIncidentCreatedDto(Incidents incidents){
        return mapper.map(incidents, IncidentsCreatedDto.class);
    }

    public static Incidents toIncidentsEntity(IncidentsDetailsDto detailsDto){
        return mapper.map(detailsDto, Incidents.class);
    }

    public static Incidents toIncidentsEntity(IncidentsDto incidentsDto){
        return mapper.map(incidentsDto, Incidents.class);
    }

    public static Incidents toIncidentsEntity(IncidentVo incidentVo){
        return mapper.map(incidentVo, Incidents.class);
    }
}
