package br.com.diazero.incidents.spring.service;

import br.com.diazero.incidents.spring.domain.dto.IncidentsDetailsDto;
import br.com.diazero.incidents.spring.domain.entity.Comments;
import br.com.diazero.incidents.spring.domain.entity.Incidents;
import br.com.diazero.incidents.spring.exception.BusinessRuleException;
import br.com.diazero.incidents.spring.mother.CommentMother;
import br.com.diazero.incidents.spring.repository.CommentsRepository;
import br.com.diazero.incidents.spring.repository.IncidentsRepository;
import br.com.diazero.incidents.spring.service.impl.IncidentsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static br.com.diazero.incidents.spring.mother.IncidentsMother.getIncident;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IncidentServiceTest {

    private IncidentsServiceImpl incidentsService;
    private IncidentsRepository incidentsRepository;
    private CommentsRepository commentsRepository;
    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        this.incidentsRepository = Mockito.mock(IncidentsRepository.class);
        this.commentsRepository = Mockito.mock(CommentsRepository.class);
        this.incidentsService = new IncidentsServiceImpl(this.incidentsRepository, this.commentsRepository);
    }

    @Test
    public void should_return_exception_when_return_empty_list(){
        //Given
        Long request = 1L;
        when(incidentsRepository.findById(any())).thenReturn(Optional.empty());

        //When
        BusinessRuleException response = assertThrows(
                BusinessRuleException.class,
                () -> incidentsService.getIncidentsById(request),
                "register not found"
        );

        //Then
        assertNotNull(response);
    }

    @ParameterizedTest
    @MethodSource("get_comments")
    public void should_update_incident_with_comments(Long id, String comments) {
        //Given
        Comments comment = CommentMother.getComment(comments, getIncident());
        Incidents expectedIncident = getIncident();
        expectedIncident.setComments(List.of(comment));
        when(incidentsRepository.findById(anyLong())).thenReturn(Optional.of(getIncident()));
        when(commentsRepository.findByIncidentId(any())).thenReturn(Collections.emptyList());
        when(commentsRepository.save(any())).thenReturn(comment);
        when(incidentsRepository.saveAndFlush(any())).thenReturn(expectedIncident);

        //When  1
        IncidentsDetailsDto response = incidentsService.updateIncident(id, comments);

        //Then
        assertNotNull(response);
        Assertions.assertTrue(response.getComments().get(0).contains(comments));
    }

    private static Stream<Arguments> get_comments(){
        return Stream.of(
            Arguments.of("21", "new comment to unit test challenge diazero"),
            Arguments.of("21", "new comment to unit test challenge diazero2")
        );
    }
}
