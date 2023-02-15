package io.cristos.petmanagement.dtos.customer;

import io.cristos.petmanagement.dtos.contact.ContactDto;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class CustomerDto implements Serializable {

    private Long customerId;
    @NotBlank(message = "First name is required.")
    @Size(min = 2, max = 255, message = "First name must be between 2 - 255 characters.")
    private String firstName;
    @NotBlank(message = "Last name is required.")
    @Size(min = 2, max = 255, message = "Last name must be between 2 - 255 characters.")
    private String lastName;
    @NotNull(message = "Date of birth is required.")
    @Past(message = "The date of birth must be in the past.")
    private LocalDate dateOfBirth;
    private LocalDate dateOfCreation;
    @NotBlank(message = "Gender is required.")
    private String gender;
    private ContactDto contact;

    @Transient
    private int age;

    public CustomerDto() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ContactDto getContact() {
        return contact;
    }

    public void setContact(ContactDto contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfCreation=" + dateOfCreation +
                ", gender='" + gender + '\'' +
                ", contact=" + contact +
                ", age=" + age +
                '}';
    }
}
