package br.com.diazero.incidents.spring.repository;

import br.com.diazero.incidents.spring.domain.entity.Comments;
import br.com.diazero.incidents.spring.domain.entity.Incidents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByIncidentId(Long id);
}
