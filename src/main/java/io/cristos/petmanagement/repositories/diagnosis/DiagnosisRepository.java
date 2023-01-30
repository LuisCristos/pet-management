package io.cristos.petmanagement.repositories.diagnosis;

import io.cristos.petmanagement.models.diagnosis.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

//    Optional<Diagnosis> findDiagnosisByPetId(Long petId);

    List<Diagnosis> findDiagnosisByPetId(Long petId);
}
