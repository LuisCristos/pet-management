package io.cristos.petmanagement.models;

import io.cristos.petmanagement.models.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.Period;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseEntity extends BaseIdCreationDate {

    @Column(
            name = "bornAt",
            nullable = false,
            columnDefinition = "DATE"
    )
    @NotNull(message = "{validation.notnull.bornat}")
    @Past(message = "{validation.past}")
    private LocalDate bornAt;
    @Transient
    private int age;
    @Column(
            name = "gender",
            nullable = false,
            columnDefinition = "ENUM('MALE', 'FEMALE', 'OTHER')"
    )
    @NotNull(message = "{validation.notnull.gender}")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public int getAge() {
        return Period.between(bornAt, LocalDate.now()).getYears();
    }
}
