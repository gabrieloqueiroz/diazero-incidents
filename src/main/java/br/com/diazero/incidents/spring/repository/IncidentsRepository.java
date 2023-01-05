package br.com.diazero.incidents.spring.repository;

import br.com.diazero.incidents.spring.domain.entity.Incidents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentsRepository extends JpaRepository<Incidents, Long> {
}
