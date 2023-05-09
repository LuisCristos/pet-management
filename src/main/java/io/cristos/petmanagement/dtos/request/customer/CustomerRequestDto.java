package io.cristos.petmanagement.dtos.request.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerRequestDto {
    @NotBlank(message = "{validation.notblank.firstname}")
    @Size(min = 2, max = 100, message = "{validation.size.input}")
    private String firstName;
    @NotBlank(message = "{validation.notblank.lastname}")
    @Size(min = 2, max = 100, message = "{validation.size.input}")
    private String lastName;
    @NotNull(message = "{validation.notnull.bornat}")
    @Past(message = "{validation.past}")
    private LocalDate bornAt;
    @NotBlank(message = "{validation.notblank.gender}")
    private String gender;
}
