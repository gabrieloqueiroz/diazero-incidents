package br.com.diazero.incidents.spring.mother;

import br.com.diazero.incidents.spring.domain.dto.IncidentsDto;
import br.com.diazero.incidents.spring.domain.entity.Comments;
import br.com.diazero.incidents.spring.domain.entity.Incidents;
import br.com.diazero.incidents.spring.domain.enuns.Status;

import java.time.LocalDateTime;
import java.util.*;

public class IncidentsMother {

    public static List<Incidents> getIncidentsList() {
        Incidents incidents = getIncident();
        return Arrays.asList(incidents, incidents, incidents);
        }

        public static List<IncidentsDto> getIncidentsDtoList(){
            IncidentsDto incidentsDtoList = getIncidentDto();

            return Arrays.asList(incidentsDtoList, incidentsDtoList, incidentsDtoList);
        }


        public static Incidents getIncident(){
            return new Incidents(21L,
                    "Test",
                    "New test",
                    Status.OPEN,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    Arrays.asList(new Comments(), new Comments()));
        }

        public static IncidentsDto getIncidentDto(){
            return new IncidentsDto(
                    21L,
                    "Test",
                    "New Test Incidents Dto",
                    LocalDateTime.now()
            );
        }
    }


