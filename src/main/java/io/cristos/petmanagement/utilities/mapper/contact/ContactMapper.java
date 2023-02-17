package io.cristos.petmanagement.utilities.mapper.contact;

import io.cristos.petmanagement.dtos.contact.ContactDto;
import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.veterinarian.VeterinarianDto;
import io.cristos.petmanagement.models.contact.Contact;

import java.util.Collection;
import java.util.List;

public interface ContactMapper {

    Contact contactRequestDtoToContact(ContactRequestDto contactRequestDto);


    ContactDto contactToContactDto(Contact contact);

    Contact contactDtoToContact(ContactDto contactDto);

    List<ContactDto> contactListToContactDtoList(Collection<Contact> contactCollection);

    ContactDto getContactDtoFromVeterinarianDto(VeterinarianDto veterinarianDto);
}
