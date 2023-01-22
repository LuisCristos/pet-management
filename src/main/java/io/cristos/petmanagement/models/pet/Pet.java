package io.cristos.petmanagement.models.pet;

import io.cristos.petmanagement.models.BaseIdDateOfCreationEntity;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Pet")
@Table(name = "pet")
@SequenceGenerator(name = "id_generator", sequenceName = "id_sequence_pet", allocationSize = 10)
public class Pet extends BaseIdDateOfCreationEntity {

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 255, message = "Name must be between 2 - 255 characters.")
    private String name;
    @Column(
            name = "gender",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    @NotBlank(message = "Gender is required.")
    @Size(min = 2, max = 10, message = "Name must be between 2 - 10 characters.")
    private String gender;
    @Column(
            name = "date_of_birth",
            nullable = false,
            columnDefinition = "DATE"
    )
    @NotNull(message = "Date of birth is required.")
    @Past(message = "Date of Birth must be in the Past.")
    private LocalDate dateOfBirth;
    @Transient
    private int age;

    @OneToMany(mappedBy = "pet")
    private List<Diagnosis> diagnosisList = new ArrayList<>();

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
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public List<Diagnosis> getDiagnosisList() {
        return diagnosisList;
    }

    public void removeDiagnosis(Diagnosis diagnosis) {
        this.diagnosisList.remove(diagnosis);
    }

    public void addDiagnosis(Diagnosis diagnosis) {
        this.diagnosisList.add(diagnosis);
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
