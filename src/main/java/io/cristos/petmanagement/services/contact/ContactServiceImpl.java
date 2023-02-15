package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.contact.ContactDto;
import io.cristos.petmanagement.dtos.customer.CustomerDto;
import io.cristos.petmanagement.dtos.veterinarian.VeterinarianDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.repositories.contact.ContactRepository;
import io.cristos.petmanagement.services.customer.CustomerService;
import io.cristos.petmanagement.services.veterinarian.VeterinarianService;
import io.cristos.petmanagement.utilities.mapper.contact.ContactMapper;
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

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, VeterinarianService veterinarianService,
                              CustomerService customerService, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.veterinarianService = veterinarianService;
        this.customerService = customerService;
        this.contactMapper = contactMapper;
    }

    //    veterinarian
    @Override
    public ContactDto findContactByVeterinarianId(Long veterinarianId, Long contactId) {

        returnVeterinarianDtoIfExists(veterinarianId);

        final String action = "found";
        Contact contact = returnContactIfExists(contactId, action);

        return contactMapper.contactToContactDto(contact);
    }

    @Override
    @Transactional
    public Contact saveContactToVeterinarianByID(Long veterinarianId, ContactDto contactDto) {

        VeterinarianDto veterinarianDto = returnVeterinarianDtoIfExists(veterinarianId);

        if (Objects.nonNull(veterinarianDto.getContactDto())) {

            logger.warn("{}, {}!",
                    "An exception occurred!", "Contact for " + contactDto + " already exists.");

            throw new IllegalArgumentException("Contact for " + contactDto + " already exists.");
        }

        veterinarianDto.setContactDto(contactDto);

        return veterinarianService.saveVeterinarian(veterinarianDto).getContact();
    }


    @Override
    @Transactional
    public Contact updateContactToVeterinarianById(Long veterinarianId, ContactDto contactDto, Long contactId) {

        VeterinarianDto veterinarianDto = returnVeterinarianDtoIfExists(veterinarianId);

        final String action = "updated";
        returnContactIfExists(contactId, action);

        veterinarianDto.setContactDto(contactDto);

        return veterinarianService.saveVeterinarian(veterinarianDto).getContact();
    }

    @Override
    @Transactional
    public void deleteContactToVeterinarianById(Long veterinarianId, Long contactId) {

        VeterinarianDto veterinarianDto = returnVeterinarianDtoIfExists(veterinarianId);

        final String action = "deleted";
        Contact contact = returnContactIfExists(contactId, action);

        veterinarianDto.setContactDto(null);

        veterinarianService.saveVeterinarian(veterinarianDto);

        contactRepository.delete(contact);
    }

    @Override
    public VeterinarianDto returnVeterinarianDtoIfExists(Long veterinarianId) {
        return veterinarianService.findVeterinarianById(veterinarianId);
    }

    @Override
    public Contact returnContactIfExists(Long contactId, String action) {

        Optional<Contact> optionalContact = Optional.of(contactRepository.findById(contactId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}!",
                            "An exception occurred!", "Contact with id: " + contactId + " cannot be " + action + " because it does not exist.",
                            new NotFoundException("Contact with id: " + contactId + " cannot be " + action + " because it does not exist."));

                    return new NotFoundException("Contact with id: " + contactId + " cannot be " + action + " because it does not exist.");
                }));

        return optionalContact.get();
    }

//    customer


    @Override
    public ContactDto findContactByCustomerId(Long customerId, Long contactId) {

        returnCustomerDtoIfExists(customerId);

        final String action = "found";
        Contact contact = returnContactIfExists(contactId, action);

        return contactMapper.contactToContactDto(contact);
    }

    @Override
    public Contact saveContactToCustomerByID(Long customerId, ContactDto contactDto) {

        CustomerDto customerDto = returnCustomerDtoIfExists(customerId);

        if (Objects.nonNull(customerDto.getContact())) {

            logger.warn("{}, {}!",
                    "An exception occurred!", "Contact for " + contactDto + " already exists.");

            throw new IllegalArgumentException("Contact for " + contactDto + " already exists.");
        }

        customerDto.setContact(contactDto);

        return customerService.saveCustomer(customerDto).getContact();
    }

    @Override
    public Contact updateContactToCustomerById(Long customerId, ContactDto contactDto, Long contactId) {

        CustomerDto customerDto = returnCustomerDtoIfExists(customerId);

        final String action = "updated";
        returnContactIfExists(contactId, action);

        customerDto.setContact(contactDto);

        return customerService.saveCustomer(customerDto).getContact();
    }

    @Override
    public void deleteContactToCustomerById(Long customerId, Long contactId) {

        CustomerDto customerDto = returnCustomerDtoIfExists(customerId);

        final String action = "deleted";
        Contact contact = returnContactIfExists(contactId, action);

        customerDto.setContact(null);

        customerService.saveCustomer(customerDto);

        contactRepository.delete(contact);
    }

    @Override
    public CustomerDto returnCustomerDtoIfExists(Long customerId) {
        return customerService.findCustomerById(customerId);
    }
}
