package io.cristos.petmanagement.dtos.veterinarian;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class VeterinarianDto {

    private Long id;
    @NotBlank(message = "Speciality is required.")
    @Size(min = 2, max = 255, message = "Speciality must be between 2 - 255 characters.")
    private String speciality;
    private LocalDate dateOfCreation;

    public VeterinarianDto(Long id, String speciality, LocalDate dateOfCreation) {
        this.id = id;
        this.speciality = speciality;
        this.dateOfCreation = dateOfCreation;
    }

    public VeterinarianDto(String speciality, LocalDate dateOfCreation) {
        this.speciality = speciality;
        this.dateOfCreation = dateOfCreation;
    }

    public VeterinarianDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
