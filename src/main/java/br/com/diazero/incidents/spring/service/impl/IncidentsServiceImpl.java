package br.com.diazero.incidents.spring.service.impl;

import br.com.diazero.incidents.spring.domain.dto.IncidentsDetailsDto;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDto;
import br.com.diazero.incidents.spring.domain.entity.Incidents;
import br.com.diazero.incidents.spring.repository.IncidentsRepository;
import br.com.diazero.incidents.spring.service.IncidentsService;
import br.com.diazero.incidents.spring.util.AbstractEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.diazero.incidents.spring.util.AbstractEntity.toIncidentDetailsDto;
import static br.com.diazero.incidents.spring.util.AbstractEntity.toIncidentDto;

@Service
public class IncidentsServiceImpl implements IncidentsService {

    private IncidentsRepository incidentsRepository;
    private ModelMapper mapper;

    @Autowired
    public IncidentsServiceImpl(IncidentsRepository incidentsRepository, ModelMapper modelMapper) {
        this.incidentsRepository = incidentsRepository;
        this.mapper = modelMapper;
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
}
