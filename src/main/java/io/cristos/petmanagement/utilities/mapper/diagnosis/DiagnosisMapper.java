package io.cristos.petmanagement.utilities.mapper.diagnosis;

import io.cristos.petmanagement.dtos.request.diagnosis.DiagnosisRequestDto;
import io.cristos.petmanagement.dtos.response.diagnosis.DiagnosisResponseDto;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;

import java.util.Collection;
import java.util.List;

public interface DiagnosisMapper {

    Diagnosis diagnosisRequestDtoToDiagnosis(DiagnosisRequestDto diagnosisRequestDto);

    Diagnosis diagnosisRequestDtoToDiagnosis(Long diagnosisId, DiagnosisRequestDto diagnosisRequestDto);

    DiagnosisResponseDto diagnosisToDiagnosisResponseDto(Diagnosis diagnosis);

    List<DiagnosisResponseDto> diagnosisListToDiagnosisResponseDtoList(Collection<Diagnosis> diagnosisCollection);

    Diagnosis createDiagnosisFromDiagnosisRequestDto(DiagnosisRequestDto diagnosisRequestDto);
}
