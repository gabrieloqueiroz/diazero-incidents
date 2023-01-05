package br.com.diazero.incidents.spring.controller;

import br.com.diazero.incidents.spring.domain.dto.IncidentsCreatedDto;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDetailsDto;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDto;
import br.com.diazero.incidents.spring.domain.vo.IncidentVo;
import br.com.diazero.incidents.spring.service.IncidentsService;
import br.com.diazero.incidents.spring.util.ConstantsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static br.com.diazero.incidents.spring.util.ConstantsUtils.INCIDENTS_SERVICE;
import static br.com.diazero.incidents.spring.util.ConstantsUtils.PATH_SEPARATOR;

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

    @GetMapping("/{id}")
    public ResponseEntity<IncidentsDetailsDto> getIncidentById(@PathVariable Long id){
        IncidentsDetailsDto incident = incidentsService.getIncidentsById(id);

        return ResponseEntity.ok(incident);
    }

    @PostMapping("create")
    public ResponseEntity<IncidentsCreatedDto> createIncident(@RequestBody IncidentVo incident, UriComponentsBuilder componentsBuilder){
        IncidentsCreatedDto savedIncident = incidentsService.createIncident(incident);

        URI uri = componentsBuilder.path(PATH_SEPARATOR + INCIDENTS_SERVICE + "{id}").buildAndExpand(savedIncident.getId()).toUri();

        return ResponseEntity.created(uri).body(savedIncident);
    }
}
