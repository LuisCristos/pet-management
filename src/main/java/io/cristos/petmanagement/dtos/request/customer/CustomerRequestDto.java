package io.cristos.petmanagement.dtos.request.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CustomerRequestDto(

        @NotBlank(message = "First name is required.")
        @Size(min = 2, max = 100, message = "First name must be between 2 - 100 characters.")
        String firstName,
        @NotBlank(message = "Last name is required.")
        @Size(min = 2, max = 100, message = "Last name must be between 2 - 100 characters.")
        String lastName,
        @NotNull(message = "Date of birth is required.")
        @Past(message = "The date of birth must be in the past.")
        LocalDate bornAt,
        @NotNull(message = "Gender is required.")
        String gender
) {

}
