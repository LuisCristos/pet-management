package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.contact.ContactDto;
import io.cristos.petmanagement.dtos.customer.CustomerDto;
import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;

public interface ContactService {

    //    veterinarian
    ContactDto findContactByVeterinarianId(Long veterinarianId, Long contactId);

    Veterinarian saveContactToVeterinarianByID(Long veterinarianId, ContactRequestDto contactRequestDto);

    Contact updateContactToVeterinarianById(Long veterinarianId, ContactDto contactDto, Long contactId);

    void deleteContactToVeterinarianById(Long veterinarianId, Long contactId);

    Veterinarian returnVeterinarianDtoIfExists(Long veterinarianId);

    Contact returnContactIfExists(Long contactId, String action);


    //    customer
    ContactDto findContactByCustomerId(Long customerId, Long contactId);

    Contact saveContactToCustomerByID(Long customerId, ContactDto contactDto);

    Contact updateContactToCustomerById(Long customerId, ContactDto contactDto, Long contactId);

    void deleteContactToCustomerById(Long customerId, Long contactId);

    CustomerDto returnCustomerDtoIfExists(Long customerId);

}
