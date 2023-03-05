package io.cristos.petmanagement.models.diagnosis;

import io.cristos.petmanagement.models.BaseIdCreationDate;
import io.cristos.petmanagement.models.pet.Pet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity(name = "Diagnosis")
@Table(name = "diagnosis")
@SequenceGenerator(name = "id_generator", sequenceName = "id_sequence_diagnosis", allocationSize = 10)
public class Diagnosis extends BaseIdCreationDate {

    @Column(
            name = "last_update",
            columnDefinition = "DATE"
    )
    @UpdateTimestamp
    private LocalDate updatedAt;
    @Column(
            nullable = false,
            name = "diagnosis",
            columnDefinition = "TEXT"
    )
    @NotBlank(message = "{validation.notblank.diagnosis}")
    private String diagnosis;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
    )
    private Pet pet;

    public Diagnosis() {
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
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
