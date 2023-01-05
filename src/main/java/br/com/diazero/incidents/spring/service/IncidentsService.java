package br.com.diazero.incidents.spring.service;

import br.com.diazero.incidents.spring.domain.dto.IncidentsDto;

import java.util.List;

public interface IncidentsService {

    List<IncidentsDto> getAllIncidents();
}
