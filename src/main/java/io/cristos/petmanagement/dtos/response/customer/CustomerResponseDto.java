package io.cristos.petmanagement.dtos.response.customer;

import java.time.LocalDate;

public record CustomerResponseDto(

        Long customerId,
        String firstName,
        String lastName,
        LocalDate bornAt,
        String gender,
        LocalDate createdAt,
        int age
) {
}
