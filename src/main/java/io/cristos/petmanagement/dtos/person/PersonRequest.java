package io.cristos.petmanagement.dtos.person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PersonRequest {

    @NotBlank(message = "Firstname is required.")
    @Size(min = 2, max = 255, message = "Firstname must be between 2 and 255 characters long")
    private String firstName;

    @NotBlank(message = "Lastname is required.")
    @Size(min = 2, max = 255, message = "Lastname must be between 2 and 255 characters long")
    private String lastName;

    @NotNull(message = "Date of Birth is required.")
    private LocalDate dateOfBirth;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
