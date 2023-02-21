package io.cristos.petmanagement.dtos.request.diagnosis;

import jakarta.validation.constraints.NotBlank;

public class DiagnosisRequestDto {

    @NotBlank(message = "{validation.notblank.diagnosis}")
    private String diagnosis;

    public DiagnosisRequestDto() {
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "DiagnosisRequestDto{" +
                "diagnosis='" + diagnosis + '\'' +
                '}';
    }
}
