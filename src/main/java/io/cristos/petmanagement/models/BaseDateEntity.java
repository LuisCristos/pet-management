package io.cristos.petmanagement.models;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;

@MappedSuperclass
public class BaseDateEntity extends BaseEntity {

    private LocalDate localDate;

    public BaseDateEntity(Long id, LocalDate localDate) {
        super(id);
        this.localDate = localDate;
    }

    public BaseDateEntity() {
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
