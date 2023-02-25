package io.cristos.petmanagement.dtos.response.customer;

import io.cristos.petmanagement.dtos.response.pet.PetResponseDto;
import io.cristos.petmanagement.models.pet.Pet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerPetResponseDto {

    private Long customerId;
    private String firstName;
    private String lastName;
    private LocalDate bornAt;
    private String gender;
    private LocalDate createdAt;
    private int age;
    private List<PetResponseDto> petList = new ArrayList<>();

    public CustomerPetResponseDto() {
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

    public List<PetResponseDto> getPetList() {
        return petList;
    }

    public void addPets(PetResponseDto pet) {
        this.petList.add(pet);
    }

    public void removePet(Pet pet) {
        this.petList.remove(pet);
    }
}
