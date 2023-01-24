package io.cristos.petmanagement.dtos.diagnosis;

import io.cristos.petmanagement.models.pet.Pet;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

public class DiagnosisDto implements Serializable {

    private Long id;

    @UpdateTimestamp
    private LocalDate lastUpdate;

    @NotBlank(message = "Diagnostic field must not be empty.")
    private String diagnosis;
    private LocalDate dateOfCreation;

    private Pet pet;

    public DiagnosisDto(Long id, LocalDate lastUpdate, String diagnosis, LocalDate dateOfCreation, Pet pet) {
        this.id = id;
        this.lastUpdate = lastUpdate;
        this.diagnosis = diagnosis;
        this.dateOfCreation = dateOfCreation;
        this.pet = pet;
    }

    public DiagnosisDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "DiagnosisDto{" +
                "id=" + id +
                ", lastUpdate=" + lastUpdate +
                ", diagnosis='" + diagnosis + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                ", pet=" + pet +
                '}';
    }
}
