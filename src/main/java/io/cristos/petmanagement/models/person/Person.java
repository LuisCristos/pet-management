package io.cristos.petmanagement.models.person;

import io.cristos.petmanagement.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Person extends BaseEntity {

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "VARCHAR(100)"
    )
    @NotBlank(message = "{validation.notblank.firstname}")
    @Size(min = 2, max = 100, message = "{validation.size.input}")
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "VARCHAR(100)"
    )
    @NotBlank(message = "{validation.notblank.lastname}")
    @Size(min = 2, max = 100, message = "{validation.size.input}")
    private String lastName;
}
