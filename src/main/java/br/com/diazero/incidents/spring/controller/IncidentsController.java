package br.com.diazero.incidents.spring.controller;

import br.com.diazero.incidents.spring.domain.dto.IncidentsDetailsDto;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDto;
import br.com.diazero.incidents.spring.service.IncidentsService;
import br.com.diazero.incidents.spring.util.ConstantsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ConstantsUtils.PATH_SEPARATOR + ConstantsUtils.INCIDENTS_SERVICE)
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
}
