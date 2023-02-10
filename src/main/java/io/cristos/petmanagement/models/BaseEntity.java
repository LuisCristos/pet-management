package io.cristos.petmanagement.models;

import io.cristos.petmanagement.models.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.time.Period;

@MappedSuperclass
public abstract class BaseEntity extends BaseIdCreationDate {

    @Column(
            name = "date_of_birth",
            nullable = false,
            columnDefinition = "DATE"
    )
    @NotNull(message = "Date of birth is required.")
    @Past(message = "The date of birth must be in the past.")
    private LocalDate dateOfBirth;
    @Transient
    private int age;
    @Column(
            name = "gender",
            nullable = false,
            columnDefinition = "ENUM('MALE', 'FEMALE', 'OTHER')"
    )
    @NotNull(message = "Gender is required.")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public BaseEntity(Long id, LocalDate dateOfCreation, LocalDate dateOfBirth, int age, Gender gender) {
        super(id, dateOfCreation);
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.gender = gender;
    }

    public BaseEntity(LocalDate dateOfBirth, int age, Gender gender) {
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.gender = gender;
    }

    public BaseEntity() {
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
