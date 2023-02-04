package io.cristos.petmanagement.dtos.pet;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class PetDto implements Serializable {

    private Long id;
    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 255, message = "Name must be between 2 - 255 characters.")
    private String name;
    @NotNull(message = "Gender is required.")
    private String gender;
    @NotNull(message = "Date of birth is required.")
    @Past(message = "Date of Birth must be in the Past.")
    private LocalDate dateOfBirth;
    private LocalDate dateOfCreation;
    @Transient
    private int age;
    private List<DiagnosisDto> diagnosisList = new ArrayList<>();

    public PetDto(Long id, String name, String gender, LocalDate dateOfBirth,
                  LocalDate dateOfCreation, int age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.dateOfCreation = dateOfCreation;
        this.age = age;
    }

    public PetDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<DiagnosisDto> getDiagnosisList() {
        return diagnosisList;
    }

    public void addDiagnosis(DiagnosisDto diagnosisDto) {
        this.diagnosisList.add(diagnosisDto);
    }

    public void removeDiagnosis(DiagnosisDto diagnosisDto) {
        this.diagnosisList.remove(diagnosisDto);
    }

    @Override
    public String toString() {
        return "PetDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfCreation=" + dateOfCreation +
                ", age=" + age +
                '}';
    }
}
