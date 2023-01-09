package br.com.diazero.incidents.spring.service.impl;

import br.com.diazero.incidents.spring.domain.dto.IncidentsCreatedDto;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDetailsDto;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDto;
import br.com.diazero.incidents.spring.domain.entity.Comments;
import br.com.diazero.incidents.spring.domain.entity.Incidents;
import br.com.diazero.incidents.spring.domain.enuns.Status;
import br.com.diazero.incidents.spring.domain.vo.IncidentVo;
import br.com.diazero.incidents.spring.exception.BusinessRuleException;
import br.com.diazero.incidents.spring.repository.CommentsRepository;
import br.com.diazero.incidents.spring.repository.IncidentsRepository;
import br.com.diazero.incidents.spring.service.IncidentsService;
import br.com.diazero.incidents.spring.util.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.diazero.incidents.spring.util.AbstractEntity.*;

@Service
public class IncidentsServiceImpl implements IncidentsService {

    private IncidentsRepository incidentsRepository;
    private CommentsRepository commentsRepository;

    @Autowired
    public IncidentsServiceImpl(IncidentsRepository incidentsRepository, CommentsRepository commentsRepository) {
        this.incidentsRepository = incidentsRepository;
        this.commentsRepository = commentsRepository;
    }

    @Override
    public List<IncidentsDto> getAllIncidents() {
        List<Incidents> incidents = incidentsRepository.findAll();

        if (incidents.isEmpty()) {
            throw new BusinessRuleException("incidents not found");
        }

        return incidents
                .stream()
                .map(AbstractEntity::toIncidentDto)
                .collect(Collectors.toList());
    }

    @Override
    public IncidentsDetailsDto getIncidentsById(Long id) {
        Incidents incident = getIncidentEntityById(id);
        return toIncidentDetailsDto(incident);
    }

    @Override
    public IncidentsCreatedDto createIncident(IncidentVo incident) {
        Incidents incidents = toIncidentsEntity(incident);
        incidents.setCreatedAt(LocalDateTime.now());
        incidents.setStatus(Status.OPEN);

        Incidents savedIncident = incidentsRepository.save(incidents);

        return toIncidentCreatedDto(savedIncident);
    }

    @Override
    public List<IncidentsDto> getLastIncidents() {
        List<Incidents> lastIncidents = incidentsRepository.findFirst20OByOrderByCreatedAtDesc();

        if (lastIncidents.isEmpty()) {
            throw new BusinessRuleException("incidents not found");
        }

        return lastIncidents
                .stream()
                .map(AbstractEntity::toIncidentDto)
                .collect(Collectors.toList());
    }

    @Override
    public IncidentsDetailsDto  updateIncident(Long id, String comments) {
        Incidents incident = getIncidentEntityById(id);

        Comments newComment = new Comments(comments, incident);
        commentsRepository.save(newComment);

        List<Comments> commentsById = commentsRepository.findByIncidentId(incident.getId());

        incident.setComments(commentsById);
        incident.setUpdatedAt(LocalDateTime.now());

        Incidents savedIncident = incidentsRepository.saveAndFlush(incident);

        return toIncidentDetailsDto(savedIncident);
    }

    @Override
    public IncidentsDetailsDto updateStatusIncident(Long id) {
        Incidents incident = getIncidentEntityById(id);

        Status newStatus = (incident.getStatus().equals(Status.OPEN)) ?
                Status.CLOSED : Status.OPEN;

        incident.setStatus(newStatus);

        if (newStatus.equals(Status.CLOSED)) {
            incident.setClosedAt(LocalDateTime.now());
        }

        Incidents updatedIncident = incidentsRepository.save(incident);

        return toIncidentDetailsDto(updatedIncident);
    }

    @Override
    public void deleteIncident(Long id) {
        commentsRepository.deleteByIncidentId(id);
        incidentsRepository.deleteById(id);
    }

    private Incidents getIncidentEntityById(Long id) {
        return incidentsRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Register not found"));
    }
}
