package io.cristos.petmanagement.utilities.mapper.diagnosis;

import io.cristos.petmanagement.dtos.request.diagnosis.DiagnosisRequestDto;
import io.cristos.petmanagement.dtos.response.diagnosis.DiagnosisResponseDto;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class DiagnosisMapperImpl implements DiagnosisMapper {

    @Override
    public Diagnosis diagnosisRequestDtoToDiagnosis(DiagnosisRequestDto diagnosisRequestDto) {

        Diagnosis diagnosis = createDiagnosisFromDiagnosisRequestDto(diagnosisRequestDto);

        return diagnosis;
    }

    @Override
    public Diagnosis diagnosisRequestDtoToDiagnosis(Long diagnosisId, DiagnosisRequestDto diagnosisRequestDto) {

        Diagnosis diagnosis = createDiagnosisFromDiagnosisRequestDto(diagnosisRequestDto);

        diagnosis.setId(diagnosisId);

        return diagnosis;
    }

    @Override
    public DiagnosisResponseDto diagnosisToDiagnosisResponseDto(Diagnosis diagnosis) {

        DiagnosisResponseDto diagnosisResponseDto = new DiagnosisResponseDto();

        diagnosisResponseDto.setId(diagnosis.getId());
        diagnosisResponseDto.setDiagnosis(diagnosis.getDiagnosis());
        diagnosisResponseDto.setDateOfCreation(diagnosis.getCreatedAt());
        diagnosisResponseDto.setUpdatedAt(diagnosis.getUpdatedAt());

        return diagnosisResponseDto;
    }


    @Override
    public List<DiagnosisResponseDto> diagnosisListToDiagnosisResponseDtoList(Collection<Diagnosis> diagnosisCollection) {

        List<DiagnosisResponseDto> diagnosisResponseDtoList = new ArrayList<>();

        for (Diagnosis diagnosis : diagnosisCollection) {

            diagnosisResponseDtoList.add(diagnosisToDiagnosisResponseDto(diagnosis));
        }

        return diagnosisResponseDtoList;
    }

    @Override
    public Diagnosis createDiagnosisFromDiagnosisRequestDto(DiagnosisRequestDto diagnosisRequestDto) {

        Diagnosis diagnosis = new Diagnosis();

        diagnosis.setDiagnosis(diagnosisRequestDto.getDiagnosis());

        return diagnosis;
    }
}
