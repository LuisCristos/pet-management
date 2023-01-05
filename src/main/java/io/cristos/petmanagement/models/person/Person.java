package io.cristos.petmanagement.models.person;

import io.cristos.petmanagement.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.time.LocalDate;

@Entity(name = "Person")
@Table(name = "person")
public class Person extends BaseEntity {

    @Column(
            name = "first_name",
            columnDefinition = "VARCHAR(255)"
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    private String lastName;
    @Column(
            name = "date_of_birth",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate dateOfBirth;
    @Transient
    private int age;

    public Person(Long id, String firstName, String lastName, LocalDate dateOfBirth) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                '}';
    }
}
