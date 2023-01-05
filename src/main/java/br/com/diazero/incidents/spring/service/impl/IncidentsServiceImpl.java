package br.com.diazero.incidents.spring.service.impl;

import br.com.diazero.incidents.spring.domain.dto.IncidentsDto;
import br.com.diazero.incidents.spring.domain.entity.Incidents;
import br.com.diazero.incidents.spring.repository.IncidentsRepository;
import br.com.diazero.incidents.spring.service.IncidentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentsServiceImpl implements IncidentsService {

    private IncidentsRepository incidentsRepository;

    @Autowired
    public IncidentsServiceImpl(IncidentsRepository incidentsRepository) {
        this.incidentsRepository = incidentsRepository;
    }

    @Override
    public List<IncidentsDto> getAllIncidents() {
        List<Incidents> incidents = incidentsRepository.findAll();


    }
}
