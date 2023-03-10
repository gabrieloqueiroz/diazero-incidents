package br.com.diazero.incidents.spring.controller;

import br.com.diazero.incidents.spring.domain.dto.IncidentsCreatedDto;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDetailsDto;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDto;
import br.com.diazero.incidents.spring.domain.vo.IncidentVo;
import br.com.diazero.incidents.spring.service.IncidentsService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static br.com.diazero.incidents.spring.util.ConstantsUtils.*;

@RestController
@RequestMapping(PATH_SEPARATOR + INCIDENTS_SERVICE)
public class IncidentsController {

    private IncidentsService incidentsService;

    @Autowired
    public IncidentsController(IncidentsService incidentsService) {
        this.incidentsService = incidentsService;
    }

    @GetMapping
    public ResponseEntity<List<IncidentsDto>> getAllIncidents(){
        List<IncidentsDto> allIncidents = incidentsService.getAllIncidents();

        return ResponseEntity.ok(allIncidents);
    }

    @GetMapping(PATH_SEPARATOR + PATH_ID)
    public ResponseEntity<IncidentsDetailsDto> getIncidentById(@PathVariable Long id){
        IncidentsDetailsDto incident = incidentsService.getIncidentsById(id);

        return ResponseEntity.ok(incident);
    }

    @GetMapping(PATH_SEPARATOR + LAST_INCIDENTS)
    public ResponseEntity<List<IncidentsDto>> getLastsIncidents(){
        List<IncidentsDto> lastIncidents = incidentsService.getLastIncidents();

        return ResponseEntity.ok(lastIncidents);
    }

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<IncidentsCreatedDto> createIncident(@RequestBody @Valid IncidentVo incident, UriComponentsBuilder componentsBuilder){
        IncidentsCreatedDto savedIncident = incidentsService.createIncident(incident);

        URI uri = componentsBuilder.path(PATH_SEPARATOR + INCIDENTS_SERVICE + PATH_ID).buildAndExpand(savedIncident.getId()).toUri();

        return ResponseEntity.created(uri).body(savedIncident);
    }

    @PatchMapping(value = PATH_SEPARATOR + PATH_ID, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<IncidentsDetailsDto> updateIncident(@PathVariable Long id, @RequestBody String comments){
        IncidentsDetailsDto savedIncident = incidentsService.updateIncident(id, comments);
        return ResponseEntity.ok(savedIncident);
    }

    @PutMapping(PATH_SEPARATOR + PATH_ID)
    public ResponseEntity<IncidentsDetailsDto> updateStatusIncident(@PathVariable Long id){
        IncidentsDetailsDto updatedIncident = incidentsService.updateStatusIncident(id);

        return ResponseEntity.ok(updatedIncident);
    }

    @DeleteMapping(PATH_SEPARATOR + PATH_ID)
    public ResponseEntity<?> deleteIncident(@PathVariable Long id){
        incidentsService.deleteIncident(id);

        return ResponseEntity.noContent().build();
    }
}
