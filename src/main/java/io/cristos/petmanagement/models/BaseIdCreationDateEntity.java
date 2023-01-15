package io.cristos.petmanagement.models;

import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@MappedSuperclass
public class BaseIdCreationDateEntity extends BaseEntity {

    @CreationTimestamp
    private LocalDate dateOfCreation = LocalDate.now();

    public BaseIdCreationDateEntity() {
    }

    public LocalDate getCreationDate() {
        return dateOfCreation;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public String toString() {
        return "BaseIdCreationDateEntity{" +
                "dateOfCreation=" + dateOfCreation +
                '}';
    }
}
