package io.cristos.petmanagement.dtos.response.pet;

import java.time.LocalDate;

public class PetResponseDto {

    private Long petId;
    private String name;
    private String gender;
    private LocalDate bornAt;
    private LocalDate createdAt;
    private String species;
    private int age;

    public PetResponseDto() {
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
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

    public LocalDate getBornAt() {
        return bornAt;
    }

    public void setBornAt(LocalDate bornAt) {
        this.bornAt = bornAt;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return "PetResponseDto{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", bornAt=" + bornAt +
                ", createdAt=" + createdAt +
                ", age=" + age +
                '}';
    }
}
