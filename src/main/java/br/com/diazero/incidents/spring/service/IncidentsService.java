package br.com.diazero.incidents.spring.service;

import br.com.diazero.incidents.spring.domain.dto.IncidentsCreatedDto;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDetailsDto;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDto;
import br.com.diazero.incidents.spring.domain.vo.IncidentVo;

import java.util.List;

public interface IncidentsService {

    List<IncidentsDto> getAllIncidents();

    IncidentsDetailsDto getIncidentsById(Long id);

    IncidentsCreatedDto createIncident(IncidentVo incident);

    void deleteIncident(Long id);

    List<IncidentsDto> getLastIncidents();

    IncidentsDetailsDto updateIncident(Long id, String comments);

    IncidentsDetailsDto updateStatusIncident(Long id);
}
