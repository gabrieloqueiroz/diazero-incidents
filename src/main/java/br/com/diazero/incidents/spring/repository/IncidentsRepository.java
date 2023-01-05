package br.com.diazero.incidents.spring.repository;

import br.com.diazero.incidents.spring.domain.entity.Incidents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentsRepository extends JpaRepository<Incidents, Long> {

    List<Incidents> findFirst20OByOrderByCreatedAtDesc();

//    @Query(value = "SELECT * FROM incidents inc ORDER BY inc.created_at DESC", nativeQuery = true)
//    List<Incidents> findLast20();
}
