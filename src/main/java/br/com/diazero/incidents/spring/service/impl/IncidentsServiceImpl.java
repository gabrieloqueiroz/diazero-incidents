package br.com.diazero.incidents.spring.service.impl;

import br.com.diazero.incidents.spring.domain.dto.IncidentsDto;
import br.com.diazero.incidents.spring.domain.entity.Incidents;
import br.com.diazero.incidents.spring.repository.IncidentsRepository;
import br.com.diazero.incidents.spring.service.IncidentsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                .map(incident ->
                        mapper.map(incident, IncidentsDto.class)
                )
                .collect(Collectors.toList());
    }
}
