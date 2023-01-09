package br.com.diazero.incidents.spring.controller;

import br.com.diazero.incidents.spring.IncidentsApplication;
import br.com.diazero.incidents.spring.config.SelfConfiguration;
import br.com.diazero.incidents.spring.domain.dto.IncidentsDetailsDto;
import br.com.diazero.incidents.spring.util.ConstantsUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@ComponentScan("br.com.diazero.incidents.spring.*")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { IncidentsApplication.class, SelfConfiguration.class})
public class IncidentsControllerTestInteg {

    @Autowired
    private WebTestClient webTest;

    @BeforeEach
    public void setup(){
        webTest.mutate().responseTimeout(Duration.ofMillis(300000)).build();
    }

    @Test
    public void should_return_incident_by_id(){
        //Given
        Long expectedId = 1L;
        String expectedName = "user1";
        String expectedDescription = "incident with SpringFramework";

        // When
        IncidentsDetailsDto response = this.webTest
                .get()
                .uri(ConstantsUtils.PATH_SEPARATOR + ConstantsUtils.INCIDENTS_SERVICE + ConstantsUtils.PATH_SEPARATOR + 1L).exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(IncidentsDetailsDto.class)
                .returnResult()
                .getResponseBody();

        //Then
        assertNotNull(response);
        assertEquals(expectedId, response.getId());
        assertEquals(expectedName, response.getName());
        assertEquals(expectedDescription, response.getDescription());
    }
}
