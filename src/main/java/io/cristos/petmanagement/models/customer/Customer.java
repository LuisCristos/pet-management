package io.cristos.petmanagement.models.customer;

import io.cristos.petmanagement.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.Period;

@Entity(name = "Customer")
@Table(name = "customer")
public class Customer extends BaseEntity {

    @Column(
            name = "first_name",
            columnDefinition = "VARCHAR(255)"
    )
    @NotBlank(message = "First name is required.")
    @Size(min = 2, max = 255, message = "First name must be between 2 - 255 characters.")
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    @NotBlank(message = "Last name is required.")
    @Size(min = 2, max = 255, message = "Last name must be between 2 - 255 characters.")
    private String lastName;
    @Column(
            name = "date_of_birth",
            nullable = false,
            columnDefinition = "DATE"
    )
    @NotNull(message = "Date of birth is required.")
    @Past(message = "The date of birth must be in the past.")
    private LocalDate dateOfBirth;
    @Transient
    private Integer age;

    public Customer(Long id, String firstName, String lastName, LocalDate dateOfBirth) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Customer(String firstName, String lastName, LocalDate dateOfBirth) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Customer() {
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

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                '}';
    }
}
