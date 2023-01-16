package io.cristos.petmanagement.repositories.pet;

import io.cristos.petmanagement.models.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}
