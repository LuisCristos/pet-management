package io.cristos.petmanagement.dtos.response.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class CustomerResponseDto {

    private Long customerId;
    private String firstName;
    private String lastName;
    private LocalDate bornAt;
    private String gender;
    private LocalDate createdAt;
    private int age;
}
