package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.contact.ContactDto;
import io.cristos.petmanagement.models.contact.Contact;

import java.util.List;

public interface ContactService {

    Contact saveContact(ContactDto contactDto);

    List<ContactDto> getAllContacts();

    ContactDto findContactById(Long contactId);

    void deleteContactById(Long contactId);

    Contact updateContact(Long contactId, ContactDto contactDto);
}
