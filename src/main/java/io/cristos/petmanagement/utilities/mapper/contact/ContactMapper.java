package io.cristos.petmanagement.utilities.mapper.contact;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.response.contact.ContactResponseDto;
import io.cristos.petmanagement.models.contact.Contact;

public interface ContactMapper {

    Contact contactRequestDtoToContact(ContactRequestDto contactRequestDto);

    Contact contactRequestDtoToContact(Long contactId, ContactRequestDto contactRequestDto);

    ContactResponseDto contactToContactResponseDto(Contact contact);

    Contact createContactFromContactRequestDto(ContactRequestDto contactRequestDto);
}
