package br.com.diazero.incidents.spring.repository;

import br.com.diazero.incidents.spring.domain.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByIncidentId(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM comments com WHERE com.incident_id = :id", nativeQuery = true)
    void deleteByIncidentId(Long id);
}
