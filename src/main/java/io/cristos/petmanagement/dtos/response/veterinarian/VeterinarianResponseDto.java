package io.cristos.petmanagement.dtos.response.veterinarian;

import java.time.LocalDate;

public record VeterinarianResponseDto(

        Long veterinarianId,
        String firstName,
        String lastName,
        LocalDate bornAt,
        String gender,
        String speciality,
        LocalDate createdAt,
        int age
) {

}
