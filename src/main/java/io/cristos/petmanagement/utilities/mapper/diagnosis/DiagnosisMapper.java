package io.cristos.petmanagement.utilities.mapper.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import io.cristos.petmanagement.dtos.pet.PetDto;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;

import java.util.Collection;
import java.util.List;

public interface DiagnosisMapper {

    DiagnosisDto diagnosisToDiagnosisDto(Diagnosis diagnosis);

    Diagnosis diagnosisDtoToDiagnosis(DiagnosisDto diagnosisDto);

    List<DiagnosisDto> diagnosisListToDiagnosisDtoList(Collection<Diagnosis> diagnosisCollection);

    PetDto diagnosisToPetDto(Collection<Diagnosis> diagnosisCollection);
}
