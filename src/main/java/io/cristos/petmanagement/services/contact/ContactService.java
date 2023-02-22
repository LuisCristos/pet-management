package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.response.contact.ContactResponseDto;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;

import java.util.List;

public interface ContactService {

    // Contact
    Contact saveContact(ContactRequestDto contactRequestDto);

    ContactResponseDto findContactById(Long contactId);

    List<ContactResponseDto> getAllContacts();

    Contact updateContactById(Long contactId, ContactRequestDto contactRequestDto);

    void deleteContactById(Long contactId);

    Contact returnContactIfExists(Long contactId);

    Contact returnContactIfExists(Veterinarian veterinarian);

    Contact returnContactIfExists(Customer customer);

    //    veterinarian
    Veterinarian saveContactToVeterinarianByID(Long veterinarianId, ContactRequestDto contactRequestDto);

    ContactResponseDto findVeterinarianContactByVeterinarianId(Long veterinarianId);

    Contact updateVeterinarianContactByVeterinarianId(Long veterinarianId, ContactRequestDto contactRequestDto, Long contactId);

    void deleteVeterinarianContactByVeterinarianId(Long veterinarianId, Long contactId);

    Veterinarian returnVeterinarianIfExists(Long veterinarianId);

    //    customer
    Customer saveContactToCustomerById(Long customerId, ContactRequestDto contactRequestDto);

    ContactResponseDto findCustomerContactByCustomerId(Long customerId, Long contactId);

    Contact updatedCustomerContactByCustomerId(Long customerId, ContactRequestDto contactRequestDto, Long contactId);

    void deleteContactToCustomerById(Long customerId, Long contactId);

    Customer returnCustomerIfExists(Long customerId);
}
