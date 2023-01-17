package io.cristos.petmanagement.controllers.contact;


import io.cristos.petmanagement.dtos.contact.ContactDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContactController {

    ResponseEntity<ContactDto> saveContact(ContactDto contactDto);

    ResponseEntity<List<ContactDto>> getAllContacts();

    ResponseEntity<ContactDto> findContactById(Long contactId);

    ResponseEntity<ContactDto> deleteContactById(Long contactId);

    ResponseEntity<ContactDto> updateContactById(Long contactId, ContactDto contactDto);
}
