package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.response.contact.ContactResponseDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;
import io.cristos.petmanagement.repositories.contact.ContactRepository;
import io.cristos.petmanagement.services.customer.CustomerService;
import io.cristos.petmanagement.services.veterinarian.VeterinarianService;
import io.cristos.petmanagement.utilities.mapper.contact.ContactMapper;
import io.cristos.petmanagement.utilities.mapper.veterinarian.VeterinarianMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);
    private final ContactRepository contactRepository;
    private final VeterinarianService veterinarianService;
    private final CustomerService customerService;
    private final ContactMapper contactMapper;
    private final VeterinarianMapper veterinarianMapper;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, VeterinarianService veterinarianService,
                              CustomerService customerService, ContactMapper contactMapper, VeterinarianMapper veterinarianMapper) {
        this.contactRepository = contactRepository;
        this.veterinarianService = veterinarianService;
        this.customerService = customerService;
        this.contactMapper = contactMapper;
        this.veterinarianMapper = veterinarianMapper;
    }

    //    veterinarian
    @Override
    @Transactional
    public Veterinarian saveContactToVeterinarianByID(Long veterinarianId, ContactRequestDto contactRequestDto) {

        Veterinarian veterinarian = returnVeterinarianDtoIfExists(veterinarianId);

        if (Objects.nonNull(veterinarian.getContact())) {

            logger.warn("{}, {}!",
                    "An exception occurred!", "Contact for " + contactRequestDto + " already exists.");

            throw new IllegalArgumentException("Contact for " + contactRequestDto + " already exists.");
        }

        Contact contact = contactMapper.contactRequestDtoToContact(contactRequestDto);

        veterinarian.setContact(contact);

        return veterinarianService.saveVeterinarianContact(veterinarian);
    }

    @Override
    @Transactional
    public ContactResponseDto findContactByVeterinarianId(Long veterinarianId) {

        Veterinarian veterinarian = returnVeterinarianDtoIfExists(veterinarianId);

        Contact contact = returnContactIfExists(veterinarian);

        return contactMapper.contactToContactResponseDto(contact);
    }

    @Override
    @Transactional
    public Contact updateContactToVeterinarianById(Long veterinarianId,
                                                   ContactRequestDto contactRequestDto, Long contactId) {

        Veterinarian veterinarian = returnVeterinarianDtoIfExists(veterinarianId);

        returnContactIfExists(veterinarian);

        Contact contact = contactMapper.contactRequestDtoToContact(contactId, contactRequestDto);

        return contactRepository.save(contact);
    }

    @Override
    @Transactional
    public void deleteContactToVeterinarianById(Long veterinarianId, Long contactId) {

        Veterinarian veterinarian = returnVeterinarianDtoIfExists(veterinarianId);

        Contact contact = returnContactIfExists(veterinarian);

        contactRepository.delete(contact);
    }

    @Override
    public Veterinarian returnVeterinarianDtoIfExists(Long veterinarianId) {
        return veterinarianService.returnVeterinarianIfExists(veterinarianId);
    }

    @Override
    public Contact returnContactIfExists(Veterinarian veterinarian) {

        if (Objects.isNull(veterinarian.getContact())) {

            throw new NotFoundException("Contact for VeterinarianId " + veterinarian.getId() + " cannot be found ");
        }

        Optional<Contact> optionalContact = contactRepository.findById(veterinarian.getContact().getId());

        return optionalContact.get();
    }

//    customer

//
//    @Override
//    public ContactDto findContactByCustomerId(Long customerId, Long contactId) {
//
//        returnCustomerDtoIfExists(customerId);
//
//        final String action = "found";
//        Contact contact = returnContactIfExists(contactId, action);
//
//        return contactMapper.contactToContactDto(contact);
//    }
//
//    @Override
//    public Contact saveContactToCustomerByID(Long customerId, ContactDto contactDto) {
//
//        CustomerDto customerDto = returnCustomerDtoIfExists(customerId);
//
//        if (Objects.nonNull(customerDto.getContact())) {
//
//            logger.warn("{}, {}!",
//                    "An exception occurred!", "Contact for " + contactDto + " already exists.");
//
//            throw new IllegalArgumentException("Contact for " + contactDto + " already exists.");
//        }
//
//        customerDto.setContact(contactDto);
//
////        return customerService.saveCustomer(customerDto).getContact();
//        return null;
//    }
//
//    @Override
//    public Contact updateContactToCustomerById(Long customerId, ContactDto contactDto, Long contactId) {
//
//        CustomerDto customerDto = returnCustomerDtoIfExists(customerId);
//
//        final String action = "updated";
//        returnContactIfExists(contactId, action);
//
//        customerDto.setContact(contactDto);
//
////        return customerService.saveCustomer(customerDto).getContact();
//        return null;
//    }
//
//    @Override
//    public void deleteContactToCustomerById(Long customerId, Long contactId) {
//
////        CustomerDto customerDto = returnCustomerDtoIfExists(customerId);
////
////        final String action = "deleted";
////        Contact contact = returnContactIfExists(contactId, action);
////
////        customerDto.setContact(null);
////
////        customerService.saveCustomer(customerDto);
////
////        contactRepository.delete(contact);
//    }
//
//    @Override
//    public CustomerDto returnCustomerDtoIfExists(Long customerId) {
////        return customerService.findCustomerById(customerId);
//        return null;
//    }
}
