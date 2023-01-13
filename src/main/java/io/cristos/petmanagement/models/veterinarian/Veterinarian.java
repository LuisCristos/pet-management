package io.cristos.petmanagement.models.veterinarian;

import io.cristos.petmanagement.models.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name = "Veterinarian")
@Table(name = "veterinarian")
@SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence_veterinarian", allocationSize = 10)
public class Veterinarian extends BaseEntity {

    @NotBlank
    @Size(min = 2, max = 255)
    private String speciality;

    public Veterinarian(Long id, String speciality) {
        super(id);
        this.speciality = speciality;
    }

    public Veterinarian(String speciality) {
        this.speciality = speciality;
    }

    public Veterinarian() {
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
