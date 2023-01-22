package io.cristos.petmanagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@MappedSuperclass
public abstract class BaseIdDateOfCreationEntity extends BaseEntity {

    @Column(
            name = "date_of_creation",
            nullable = false,
            columnDefinition = "DATE"
    )
    @CreationTimestamp
    private LocalDate dateOfCreation = LocalDate.now();

    public BaseIdDateOfCreationEntity() {
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
