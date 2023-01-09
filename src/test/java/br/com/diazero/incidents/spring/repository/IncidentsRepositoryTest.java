package br.com.diazero.incidents.spring.repository;

import br.com.diazero.incidents.spring.config.SelfConfiguration;
import br.com.diazero.incidents.spring.domain.entity.Incidents;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@EnableAutoConfiguration
@EntityScan("br.com.diazero.incidents.spring.*")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { SelfConfiguration.class })
public class IncidentsRepositoryTest {

    @Autowired
    private IncidentsRepository incidentsRepository;

    @Test
    public void should_return_20_last_incidents(){
        //Given - When
        List<Incidents> response = incidentsRepository.findFirst20OByOrderByCreatedAtDesc();

        //Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(20, response.size());
    }
}
