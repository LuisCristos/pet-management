package io.cristos.petmanagement.dtos.response.diagnosis;

import java.time.LocalDate;

public class DiagnosisResponseDto {
    private Long id;
    private LocalDate updatedAt;
    private String diagnosis;
    private LocalDate dateOfCreation;

    public DiagnosisResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public String toString() {
        return "DiagnosisResponseDto{" +
                "id=" + id +
                ", lastUpdate=" + updatedAt +
                ", diagnosis='" + diagnosis + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }
}
