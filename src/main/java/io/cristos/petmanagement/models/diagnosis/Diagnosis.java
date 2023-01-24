package io.cristos.petmanagement.models.diagnosis;

import io.cristos.petmanagement.models.BaseIdDateOfCreationEntity;
import io.cristos.petmanagement.models.pet.Pet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity(name = "Diagnosis")
@Table(name = "diagnosis")
@SequenceGenerator(name = "id_generator", sequenceName = "id_sequence_diagnosis", allocationSize = 10)
public class Diagnosis extends BaseIdDateOfCreationEntity {

    @Column(
            name = "last_update",
            columnDefinition = "DATE"
    )
    @UpdateTimestamp
    private LocalDate lastUpdate;
    @Column(
            nullable = false,
            name = "diagnosis",
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "Diagnostic field must not be empty.")
    private String diagnosis;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pet pet;

    public Diagnosis(LocalDate lastUpdate, String diagnosis) {
        this.lastUpdate = lastUpdate;
        this.diagnosis = diagnosis;
    }

    public Diagnosis() {
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

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
