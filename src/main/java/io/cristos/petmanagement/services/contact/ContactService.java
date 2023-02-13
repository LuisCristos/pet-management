package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.contact.ContactDto;
import io.cristos.petmanagement.models.contact.Contact;

public interface ContactService {


    ContactDto findContactByVeterinarianId(Long veterinarianId, Long contactId);

    Contact saveContactToVeterinarianByID(Long veterinarianId, ContactDto contactDto);

    Contact updateContactToVeterinarianById(Long veterinarianId, ContactDto contactDto, Long contactId);

    void deleteContactToVeterinarianById(Long veterinarianId, Long contactId);

}
