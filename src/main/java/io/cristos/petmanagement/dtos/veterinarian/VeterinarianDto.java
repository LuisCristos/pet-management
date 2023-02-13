package io.cristos.petmanagement.dtos.veterinarian;

import io.cristos.petmanagement.dtos.contact.ContactDto;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;

@Component
public class VeterinarianDto implements Serializable {

    private Long veterinarianId;
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
    @NotBlank(message = "Speciality is required.")
    @Size(min = 2, max = 255, message = "Speciality must be between 2 - 255 characters.")
    private String speciality;
    @NotNull(message = "Gender is required.")
    private String gender;
    private ContactDto contactDto;
    @Transient
    private int age;

    public VeterinarianDto() {
    }

    public Long getVeterinarianId() {
        return veterinarianId;
    }

    public void setVeterinarianId(Long veterinarianId) {
        this.veterinarianId = veterinarianId;
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

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ContactDto getContactDto() {
        return contactDto;
    }

    public void setContactDto(ContactDto contactDto) {
        this.contactDto = contactDto;
    }

    @Override
    public String toString() {
        return "VeterinarianDto{" +
                "veterinarianId=" + veterinarianId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfCreation=" + dateOfCreation +
                ", speciality='" + speciality + '\'' +
                ", gender='" + gender + '\'' +
                ", contactDto=" + contactDto +
                ", age=" + age +
                '}';
    }
}
