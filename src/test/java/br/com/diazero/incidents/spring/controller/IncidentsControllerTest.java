package br.com.diazero.incidents.spring.controller;

import br.com.diazero.incidents.spring.domain.dto.IncidentsDto;
import br.com.diazero.incidents.spring.mother.IncidentsMother;
import br.com.diazero.incidents.spring.service.IncidentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IncidentsControllerTest{

    private IncidentsController incidentsController;
    private IncidentsService incidentsService;

    @BeforeEach
    public void setup(){
        incidentsService = Mockito.mock(IncidentsService.class);
        incidentsController = new IncidentsController(incidentsService);
    }

    @Test
    public void should_return_all_incidents(){
        //Given
        List<IncidentsDto> incidentsEntity = IncidentsMother.getIncidentsDtoList();
        Mockito.when(incidentsService.getAllIncidents()).thenReturn(incidentsEntity);

        //When
        ResponseEntity<List<IncidentsDto>> response = incidentsController.getAllIncidents();

        //Then
        List<IncidentsDto> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(3, responseBody.size());
    }

}
