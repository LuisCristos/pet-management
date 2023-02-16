package io.cristos.petmanagement.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
public abstract class BaseIdCreationDate implements Serializable {

    @Id
    @Column(
            name = "id",
            nullable = false,
            updatable = false,
            unique = true
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_generator"
    )
    private Long id;
    @Column(
            name = "date_of_creation",
            nullable = false,
            columnDefinition = "DATE"
    )
    @CreationTimestamp
    private LocalDate createdAt = LocalDate.now();

    public BaseIdCreationDate(Long id, LocalDate createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public BaseIdCreationDate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
