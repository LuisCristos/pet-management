package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.contact.ContactDto;
import io.cristos.petmanagement.dtos.customer.CustomerDto;
import io.cristos.petmanagement.dtos.veterinarian.VeterinarianDto;
import io.cristos.petmanagement.models.contact.Contact;

public interface ContactService {

    //    veterinarian
    ContactDto findContactByVeterinarianId(Long veterinarianId, Long contactId);

    Contact saveContactToVeterinarianByID(Long veterinarianId, ContactDto contactDto);

    Contact updateContactToVeterinarianById(Long veterinarianId, ContactDto contactDto, Long contactId);

    void deleteContactToVeterinarianById(Long veterinarianId, Long contactId);

    VeterinarianDto returnVeterinarianDtoIfExists(Long veterinarianId);

    Contact returnContactIfExists(Long contactId, String action);

    //    customer
    ContactDto findContactByCustomerId(Long customerId, Long contactId);

    Contact saveContactToCustomerByID(Long customerId, ContactDto contactDto);

    Contact updateContactToCustomerById(Long customerId, ContactDto contactDto, Long contactId);

    void deleteContactToCustomerById(Long customerId, Long contactId);

    CustomerDto returnCustomerDtoIfExists(Long customerId);

}
