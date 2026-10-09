package br.com.follon.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "badges")
public class BadgeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hackathon_id", nullable = false)
    private HackathonEntity hackathon;

    @JdbcTypeCode(SqlTypes.VARBINARY)
    @Column(name = "foto", columnDefinition = "bytea")
    private byte[] foto;
}
