package io.cristos.petmanagement.utilities.mapper.contact;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.response.contact.ContactResponseDto;
import io.cristos.petmanagement.models.contact.Contact;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ContactMapperImpl implements ContactMapper {

    @Override
    public Contact contactRequestDtoToContact(ContactRequestDto contactRequestDto) {

        Contact contact = createContactFromContactRequestDto(contactRequestDto);

        return contact;
    }

    @Override
    public Contact contactRequestDtoToContact(Long contactId, ContactRequestDto contactRequestDto) {

        Contact contactToUpdate = createContactFromContactRequestDto(contactRequestDto);

        contactToUpdate.setId(contactId);

        return contactToUpdate;
    }

    @Override
    public ContactResponseDto contactToContactResponseDto(Contact contact) {

        ContactResponseDto contactResponseDto = new ContactResponseDto();

        contactResponseDto.setContactId(contact.getId());
        contactResponseDto.setStreet(contact.getStreet());
        contactResponseDto.setHouseNumber(contact.getHouseNumber());
        contactResponseDto.setCity(contact.getCity());
        contactResponseDto.setZipCode(contact.getZipCode());

        for (String phoneNumber : contact.getPhoneNumberList()) {
            contactResponseDto.addPhoneNumber(phoneNumber);
        }

        contactResponseDto.setEmail(contact.getEmail());

        return contactResponseDto;
    }

    @Override
    public List<ContactResponseDto> contactListToContactResponseDtoList(Collection<Contact> contactCollection) {

        List<ContactResponseDto> contactResponseDtoList = new ArrayList<>();

        for (Contact contact : contactCollection) {

            contactResponseDtoList.add(contactToContactResponseDto(contact));

        }

        return contactResponseDtoList;
    }

    @Override
    public Contact createContactFromContactRequestDto(ContactRequestDto contactRequestDto) {

        Contact contact = new Contact();

        contact.setCity(contactRequestDto.getCity());
        contact.setStreet(contactRequestDto.getStreet());
        contact.setHouseNumber(contactRequestDto.getHouseNumber());
        contact.setZipCode(contactRequestDto.getZipCode());
        contact.setEmail(contactRequestDto.getEmail());

        for (String phoneNumber : contactRequestDto.getPhoneNumberList()) {
            contact.addPhoneNumber(phoneNumber);
        }

        return contact;
    }
}
