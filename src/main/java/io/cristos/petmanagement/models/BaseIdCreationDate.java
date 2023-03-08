package io.cristos.petmanagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@MappedSuperclass
public abstract class BaseIdCreationDate extends BaseId {


    @Column(
            name = "date_of_creation",
            nullable = false,
            columnDefinition = "DATE"
    )
    @CreationTimestamp
    private LocalDate createdAt;

    public BaseIdCreationDate(Long id, LocalDate createdAt) {
        super(id);
        this.createdAt = createdAt;
    }

    public BaseIdCreationDate(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public BaseIdCreationDate() {
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
