package io.cristos.petmanagement.models.person;

import io.cristos.petmanagement.models.BaseEntity;
import io.cristos.petmanagement.models.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@MappedSuperclass
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

    public Person(LocalDate bornAt, int age, Gender gender, String firstName, String lastName) {
        super(bornAt, age, gender);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
