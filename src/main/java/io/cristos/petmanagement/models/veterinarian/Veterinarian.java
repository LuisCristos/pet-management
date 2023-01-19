package io.cristos.petmanagement.models.veterinarian;

import io.cristos.petmanagement.models.person.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity(name = "Veterinarian")
@Table(name = "veterinarian")
@SequenceGenerator(name = "id_generator", sequenceName = "id_sequence_veterinarian", allocationSize = 10)
public class Veterinarian extends Person {

    @Column(
            name = "speciality",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    @NotBlank(message = "Speciality is required.")
    @Size(min = 2, max = 255, message = "Speciality must be between 2 - 255 characters.")
    private String speciality;

    public Veterinarian(String firstName, String lastName, LocalDate dateOfBirth, String speciality) {
        super(firstName, lastName, dateOfBirth);
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

    @Override
    public String toString() {
        return "Veterinarian{" +
                "speciality='" + speciality + '\'' +
                '}';
    }
}
