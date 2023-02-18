package io.cristos.petmanagement.utilities.mapper.contact;

import io.cristos.petmanagement.dtos.contact.ContactDto;
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

    @Override
    public Contact contactRequestDtoToContact(Long contactId, ContactRequestDto contactRequestDto) {

        Contact contactToUpdate = new Contact();

        contactToUpdate.setId(contactId);
        contactToUpdate.setCity(contactRequestDto.getCity());
        contactToUpdate.setStreet(contactRequestDto.getStreet());
        contactToUpdate.setHouseNumber(contactRequestDto.getHouseNumber());
        contactToUpdate.setZipCode(contactRequestDto.getZipCode());
        contactToUpdate.setEmail(contactRequestDto.getEmail());

        for (String phoneNumber : contactRequestDto.getPhoneNumberList()) {
            contactToUpdate.addPhoneNumber(phoneNumber);
        }

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

}
