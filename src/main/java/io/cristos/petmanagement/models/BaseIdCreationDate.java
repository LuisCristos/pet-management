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
    private LocalDate dateOfCreation = LocalDate.now();

    public BaseIdCreationDate(Long id, LocalDate dateOfCreation) {
        this.id = id;
        this.dateOfCreation = dateOfCreation;
    }

    public BaseIdCreationDate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
