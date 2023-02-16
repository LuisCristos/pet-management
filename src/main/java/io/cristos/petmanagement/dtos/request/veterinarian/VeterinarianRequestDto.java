package io.cristos.petmanagement.dtos.request.veterinarian;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record VeterinarianRequestDto(

        @NotBlank(message = "First name is required.")
        @Size(min = 2, max = 255, message = "First name must be between 2 - 255 characters.")
        String firstName,
        @NotBlank(message = "Last name is required.")
        @Size(min = 2, max = 255, message = "Last name must be between 2 - 255 characters.")
        String lastName,
        @NotNull(message = "Date of birth is required.")
        @Past(message = "The date of birth must be in the past.")
        LocalDate bornAt,
        @NotNull(message = "Gender is required.")
        String gender,
        @NotBlank(message = "Speciality is required.")
        @Size(min = 2, max = 255, message = "Speciality must be between 2 - 255 characters.")
        String speciality
) {

}
