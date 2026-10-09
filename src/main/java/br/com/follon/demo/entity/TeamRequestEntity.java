package br.com.follon.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "team_request")
public class TeamRequestEntity {

    @EmbeddedId
    private TeamRequestId id;

    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    private TeamEntity team;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false, length = 30)
    private String status;
}
