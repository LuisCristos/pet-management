package io.cristos.petmanagement.utilities.mapper.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;
import io.cristos.petmanagement.utilities.mapper.genderconverter.GenderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class DiagnosisMapperImpl implements DiagnosisMapper {

    private final GenderConverter genderConverter;

    @Autowired
    public DiagnosisMapperImpl(GenderConverter genderConverter) {
        this.genderConverter = genderConverter;
    }

    @Override
    public DiagnosisDto diagnosisToDiagnosisDto(Diagnosis diagnosis) {

        DiagnosisDto diagnosisDto = new DiagnosisDto();

        diagnosisDto.setId(diagnosis.getId());
        diagnosisDto.setDiagnosis(diagnosis.getDiagnosis());
        diagnosisDto.setLastUpdate(diagnosis.getLastUpdate());
        diagnosisDto.setDateOfCreation(diagnosis.getCreatedAt());

        return diagnosisDto;
    }

    @Override
    public Diagnosis diagnosisDtoToDiagnosis(DiagnosisDto diagnosisDto) {

        Diagnosis diagnosis = new Diagnosis();

        diagnosis.setId(diagnosisDto.getId());
        diagnosis.setDiagnosis(diagnosisDto.getDiagnosis());
        diagnosis.setLastUpdate(diagnosisDto.getLastUpdate());
        diagnosis.setCreatedAt(diagnosisDto.getDateOfCreation());

        return diagnosis;
    }

    @Override
    public List<DiagnosisDto> diagnosisListToDiagnosisDtoList(Collection<Diagnosis> diagnosisCollection) {

        List<DiagnosisDto> diagnosisToDiagnosisDtoList = new ArrayList<>();

        for (Diagnosis diagnosis : diagnosisCollection) {

            DiagnosisDto diagnosisDto = diagnosisToDiagnosisDto(diagnosis);

            diagnosisToDiagnosisDtoList.add(diagnosisDto);
        }

        return diagnosisToDiagnosisDtoList;
    }
}
