package io.cristos.petmanagement.repositories;

import io.cristos.petmanagement.models.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
