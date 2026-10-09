package br.com.follon.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 100)
    private String city;

    @Column(columnDefinition = "TEXT")
    private String skills;

    @JdbcTypeCode(SqlTypes.VARBINARY)
    @Column(name = "foto", columnDefinition = "bytea")
    private byte[] foto;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "field_of_interest")
    private String[] fieldOfInterest;
}
