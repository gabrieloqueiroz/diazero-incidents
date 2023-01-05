package br.com.diazero.incidents.spring.service.impl;

import br.com.diazero.incidents.spring.domain.dto.IncidentsCreatedDto;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDetailsDto;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDto;
import br.com.diazero.incidents.spring.domain.entity.Incidents;
import br.com.diazero.incidents.spring.domain.enuns.Status;
import br.com.diazero.incidents.spring.domain.vo.IncidentVo;
import br.com.diazero.incidents.spring.repository.IncidentsRepository;
import br.com.diazero.incidents.spring.service.IncidentsService;
import br.com.diazero.incidents.spring.util.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.diazero.incidents.spring.util.AbstractEntity.*;

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

        if (incidents.isEmpty()){
            throw new RuntimeException();
        }

        return incidents
                .stream()
                .map(AbstractEntity::toIncidentDto)
                .collect(Collectors.toList());
    }
    @Override
    public IncidentsDetailsDto getIncidentsById(Long id) {
        Incidents incident = incidentsRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        return toIncidentDetailsDto(incident);
    }

    @Override
    @Transactional
    public IncidentsCreatedDto createIncident(IncidentVo incident) {
        Incidents incidents = toIncidentsEntity(incident);
        incidents.setCreatedAt(LocalDateTime.now());
        incidents.setStatus(Status.OPEN);

        Incidents savedIncident = incidentsRepository.save(incidents);

        return toIncidentCreatedDto(savedIncident);
    }
}
