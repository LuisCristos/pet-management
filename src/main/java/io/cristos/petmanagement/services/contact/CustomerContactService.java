package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.response.contact.ContactResponseDto;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.customer.Customer;

public interface CustomerContactService {

    //    customer
    Customer saveContactToCustomerById(Long customerId, ContactRequestDto contactRequestDto);

    ContactResponseDto findCustomerContactByCustomerId(Long customerId, Long contactId);

    Contact updatedCustomerContactByCustomerId(Long customerId, ContactRequestDto contactRequestDto, Long contactId);

    void deleteContactToCustomerById(Long customerId, Long contactId);

    Customer returnCustomerIfExists(Long customerId);

    Contact returnContactIfExists(Customer customer);

}
