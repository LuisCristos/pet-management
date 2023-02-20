package io.cristos.petmanagement.dtos.request.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PetRequestDto {
    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 255, message = "Name must be between 2 - 255 characters.")
    private String name;
    @NotNull(message = "Gender is required.")
    private String gender;
    @NotNull(message = "Date of birth is required.")
    @Past(message = "Date of Birth must be in the Past.")
    private LocalDate bornAt;
//    private List<DiagnosisDto> diagnosisList = new ArrayList<>();

    public PetRequestDto() {
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

//    public List<DiagnosisDto> getDiagnosisList() {
//        return diagnosisList;
//    }
//
//    public void setDiagnosisList(List<DiagnosisDto> diagnosisList) {
//        this.diagnosisList = diagnosisList;
//    }

    @Override
    public String toString() {
        return "PetRequestDto{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", bornAt=" + bornAt +
                '}';
    }
}
