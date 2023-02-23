package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.response.contact.ContactResponseDto;
import io.cristos.petmanagement.models.contact.Contact;

import java.util.List;

public interface ContactService {

    Contact saveContact(ContactRequestDto contactRequestDto);

    ContactResponseDto findContactById(Long contactId);

    List<ContactResponseDto> getAllContacts();

    Contact updateContactById(Long contactId, ContactRequestDto contactRequestDto);

    void deleteContactById(Long contactId);

    Contact returnContactIfExists(Long contactId);
}
