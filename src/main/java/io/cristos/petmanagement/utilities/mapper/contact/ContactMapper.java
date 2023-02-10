package io.cristos.petmanagement.utilities.mapper.contact;

import io.cristos.petmanagement.dtos.contact.ContactDto;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ContactMapper {

    ContactDto contactToContactDto(Contact contact);

    ContactDto getContactFromVeterinarian(Optional<Veterinarian> optionalVeterinarian);

    Contact contactDtoToContact(ContactDto contactDto);

    List<ContactDto> contactListToContactDtoList(Collection<Contact> contactCollection);
}
