package br.com.diazero.incidents.spring.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "incident_id")
    private Incidents incident;

    public Comments(String comment, Incidents incidents) {
        this.comment = comment;
        this.incident = incidents;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return createdAt + " " + comment.trim();
    }
}
