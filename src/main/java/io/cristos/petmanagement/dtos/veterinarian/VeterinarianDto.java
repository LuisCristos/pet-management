package io.cristos.petmanagement.dtos.veterinarian;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class VeterinarianDto {

    private Long id;
    @NotBlank(message = "Speciality is required.")
    @Size(min = 2, max = 255, message = "Speciality must be between 2 - 255 characters.")
    private String speciality;

    public VeterinarianDto(Long id, String speciality) {
        this.id = id;
        this.speciality = speciality;
    }

    public VeterinarianDto(String speciality) {
        this.speciality = speciality;
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
}
