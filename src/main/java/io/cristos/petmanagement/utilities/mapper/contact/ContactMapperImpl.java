package io.cristos.petmanagement.utilities.mapper.contact;

import io.cristos.petmanagement.dtos.contact.ContactDto;
import io.cristos.petmanagement.dtos.veterinarian.VeterinarianDto;
import io.cristos.petmanagement.models.contact.Contact;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ContactMapperImpl implements ContactMapper {

    @Override
    public ContactDto contactToContactDto(Contact contact) {

        ContactDto contactDto = new ContactDto();

        contactDto.setContactId(contact.getId());
        contactDto.setEmail(contact.getEmail());
        contactDto.setStreet(contact.getStreet());
        contactDto.setHouseNumber(contact.getHouseNumber());
        contactDto.setCity(contact.getCity());
        contactDto.setZipCode(contact.getZipCode());

        for (String phoneNumber : contact.getPhoneNumberList()) {
            contactDto.addPhoneNumber(phoneNumber);
        }

        return contactDto;
    }

    @Override
    public Contact contactDtoToContact(ContactDto contactDto) {

        Contact contact = new Contact();

        contact.setId(contactDto.getContactId());
        contact.setEmail(contactDto.getEmail());
        contact.setStreet(contactDto.getStreet());
        contact.setHouseNumber(contactDto.getHouseNumber());
        contact.setCity(contactDto.getCity());
        contact.setZipCode(contactDto.getZipCode());

        for (String phoneNumberInList : contactDto.getPhoneNumberList()) {
            contact.addPhoneNumber(phoneNumberInList);
        }

        return contact;
    }

    @Override
    public List<ContactDto> contactListToContactDtoList(Collection<Contact> contactCollection) {

        List<ContactDto> contactListToContactDtoList = new ArrayList<>();

        for (Contact contact : contactCollection) {

            ContactDto contactDto = contactToContactDto(contact);

            contactListToContactDtoList.add(contactDto);
        }

        return contactListToContactDtoList;
    }

    @Override
    public ContactDto getContactDtoFromVeterinarianDto(VeterinarianDto veterinarianDto) {

        ContactDto contactDto = new ContactDto();
        contactDto.setContactId(veterinarianDto.getContactDto().getContactId());
        contactDto.setStreet(veterinarianDto.getContactDto().getStreet());
        contactDto.setHouseNumber(veterinarianDto.getContactDto().getHouseNumber());
        contactDto.setCity(veterinarianDto.getContactDto().getCity());
        contactDto.setZipCode(veterinarianDto.getContactDto().getZipCode());
        contactDto.setEmail(veterinarianDto.getContactDto().getEmail());

        return contactDto;
    }

}
