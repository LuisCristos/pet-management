package io.cristos.petmanagement.utilities.mapper.diagnosis;

import io.cristos.petmanagement.dtos.diagnosis.DiagnosisDto;
import io.cristos.petmanagement.dtos.pet.PetDto;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class DiagnosisMapperImpl implements DiagnosisMapper {
    @Override
    public DiagnosisDto diagnosisToDiagnosisDto(Diagnosis diagnosis) {

        DiagnosisDto diagnosisDto = new DiagnosisDto();

        diagnosisDto.setId(diagnosis.getId());
        diagnosisDto.setDiagnosis(diagnosis.getDiagnosis());
        diagnosisDto.setLastUpdate(diagnosis.getLastUpdate());
        diagnosisDto.setDateOfCreation(diagnosis.getDateOfCreation());

        return diagnosisDto;
    }

    @Override
    public Diagnosis diagnosisDtoToDiagnosis(DiagnosisDto diagnosisDto) {

        Diagnosis diagnosis = new Diagnosis();

        diagnosis.setId(diagnosisDto.getId());
        diagnosis.setDiagnosis(diagnosisDto.getDiagnosis());
        diagnosis.setLastUpdate(diagnosisDto.getLastUpdate());
        diagnosis.setDateOfCreation(diagnosisDto.getDateOfCreation());

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

    @Override
    public PetDto diagnosisToPetDto(Collection<Diagnosis> diagnosisList) {

        PetDto petDto = null;
        DiagnosisDto diagnosisDto;

        for (Diagnosis diagnosis : diagnosisList) {
            petDto = new PetDto();

            petDto.setId(diagnosis.getPet().getId());
            petDto.setName(diagnosis.getPet().getName());
            petDto.setGender(diagnosis.getPet().getGender());
            petDto.setDateOfBirth(diagnosis.getPet().getDateOfBirth());
            petDto.setDateOfCreation(diagnosis.getPet().getDateOfCreation());
            petDto.setAge(diagnosis.getPet().getAge());

            for (Diagnosis diagnosis1 : diagnosisList) {
                diagnosisDto = new DiagnosisDto();

                diagnosisDto.setId(diagnosis1.getId());
                diagnosisDto.setDiagnosis(diagnosis1.getDiagnosis());
                diagnosisDto.setDateOfCreation(diagnosis1.getDateOfCreation());
                diagnosisDto.setLastUpdate(diagnosis1.getLastUpdate());

                petDto.addDiagnosis(diagnosisDto);
            }
        }
        return petDto;
    }
}
