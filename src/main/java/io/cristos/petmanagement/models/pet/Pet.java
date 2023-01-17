package io.cristos.petmanagement.models.pet;

import io.cristos.petmanagement.models.BaseIdCreationDateEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.Period;

@Entity(name = "Pet")
@Table(name = "pet")
@SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence_pet", allocationSize = 10)
public class Pet extends BaseIdCreationDateEntity {

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 255, message = "Name must be between 2 - 255 characters.")
    private String name;
    @Column(
            name = "gender",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    @NotBlank(message = "Gender is required.")
    @Size(min = 2, max = 10, message = "Name must be between 2 - 10 characters.")
    private String gender;
    @Column(
            name = "date_of_birth",
            nullable = false,
            columnDefinition = "DATE"
    )
    //    @NotNull(message = "Date of birth is required.")
//    @Past(message = "The date of birth must be in the past.")
//    @Pattern(regexp = "yyyy-mm-dd", message = "Date of Birth should be in this format. yyyy-mm-dd")
    private LocalDate dateOfBirth;
    @Transient
    private int age;

    public Pet(String name, String gender, LocalDate dateOfBirth) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Pet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirt) {
        this.dateOfBirth = dateOfBirt;
    }

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirt=" + dateOfBirth +
                ", age=" + age +
                "} " + super.toString();
    }
}
