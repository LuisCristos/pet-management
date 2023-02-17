package io.cristos.petmanagement.dtos.response.veterinarian;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;

import java.time.LocalDate;

public class VeterinarianContactResponseDto {

    private Long veterinarianId;
    private String firstName;
    private String lastName;
    private LocalDate bornAt;
    private String gender;
    private String speciality;
    private LocalDate createdAt;
    private int age;
    private ContactRequestDto contactRequestDto;

    public VeterinarianContactResponseDto() {
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

    public LocalDate getBornAt() {
        return bornAt;
    }

    public void setBornAt(LocalDate bornAt) {
        this.bornAt = bornAt;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ContactRequestDto getContactRequestDto() {
        return contactRequestDto;
    }

    public void setContactRequestDto(ContactRequestDto contactRequestDto) {
        this.contactRequestDto = contactRequestDto;
    }
}
