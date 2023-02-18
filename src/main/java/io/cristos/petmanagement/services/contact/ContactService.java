package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.response.contact.ContactResponseDto;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;

public interface ContactService {

    //    veterinarian
    Veterinarian saveContactToVeterinarianByID(Long veterinarianId, ContactRequestDto contactRequestDto);

    ContactResponseDto findContactByVeterinarianId(Long veterinarianId);

    Contact updateContactToVeterinarianById(Long veterinarianId, ContactRequestDto contactRequestDto, Long contactId);

    void deleteContactToVeterinarianById(Long veterinarianId, Long contactId);

    Veterinarian returnVeterinarianDtoIfExists(Long veterinarianId);

    Contact returnContactIfExists(Veterinarian veterinarian);

//
//    //    customer
//    ContactDto findContactByCustomerId(Long customerId, Long contactId);
//
//    Contact saveContactToCustomerByID(Long customerId, ContactDto contactDto);
//
//    Contact updateContactToCustomerById(Long customerId, ContactDto contactDto, Long contactId);
//
//    void deleteContactToCustomerById(Long customerId, Long contactId);
//
//    CustomerDto returnCustomerDtoIfExists(Long customerId);

}
