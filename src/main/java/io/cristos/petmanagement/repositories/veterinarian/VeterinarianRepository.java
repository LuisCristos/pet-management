package io.cristos.petmanagement.repositories.veterinarian;

import io.cristos.petmanagement.models.veterinarian.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {
}
