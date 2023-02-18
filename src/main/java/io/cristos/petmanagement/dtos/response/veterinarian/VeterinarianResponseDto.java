package io.cristos.petmanagement.dtos.response.veterinarian;

import java.time.LocalDate;

public class VeterinarianResponseDto {
    private Long veterinarianId;
    private String firstName;
    private String lastName;
    private LocalDate bornAt;
    private String gender;
    private String speciality;
    private LocalDate createdAt;
    private int age;

    public VeterinarianResponseDto() {
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

    @Override
    public String toString() {
        return "VeterinarianResponseDto{" +
                "veterinarianId=" + veterinarianId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bornAt=" + bornAt +
                ", gender='" + gender + '\'' +
                ", speciality='" + speciality + '\'' +
                ", createdAt=" + createdAt +
                ", age=" + age +
                '}';
    }
}
