package io.cristos.petmanagement.models.pet;

import io.cristos.petmanagement.models.BaseIdCreationDateEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.time.LocalDate;

@Entity(name = "Pet")
@Table(name = "pet")
@SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence_pet", allocationSize = 10)
public class Pet extends BaseIdCreationDateEntity {

    private String name;
    private String gender;
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
        return age;
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
