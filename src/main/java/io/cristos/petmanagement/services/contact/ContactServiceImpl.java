package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.response.contact.ContactResponseDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.repositories.contact.ContactRepository;
import io.cristos.petmanagement.services.customer.CustomerService;
import io.cristos.petmanagement.services.veterinarian.VeterinarianService;
import io.cristos.petmanagement.utilities.mapper.contact.ContactMapper;
import io.cristos.petmanagement.utilities.mapper.veterinarian.VeterinarianMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
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

    // Contact

    @Override
    public Contact saveContact(ContactRequestDto contactRequestDto) {

        return contactRepository.save(contactMapper.contactRequestDtoToContact(contactRequestDto));
    }

    @Override
    public ContactResponseDto findContactById(Long contactId) {

        Contact contact = returnContactIfExists(contactId);

        return contactMapper.contactToContactResponseDto(contact);
    }

    @Override
    public List<ContactResponseDto> getAllContacts() {

        Collection<Contact> contactCollection = contactRepository.findAll();

        if (contactCollection.isEmpty()) {

            logger.info("Retrieved empty List.");
            return Collections.emptyList();
        }

        return contactMapper.contactListToContactResponseDtoList(contactCollection);
    }

    @Override
    public Contact returnContactIfExists(Long contactId) {

        Optional<Contact> optionalContact = Optional.ofNullable(contactRepository.findById(contactId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}! An exception occurred!",
                            "An exception occurred!", "Contact with id: " + contactId + " cannot be found.",
                            new NotFoundException("Contact with id: " + contactId + " cannot be found."));

                    return new NotFoundException("Contact with id: " + contactId + " cannot be found.");
                }));

        return optionalContact.get();
    }

    @Override
    public Contact updateContactById(Long contactId, ContactRequestDto contactRequestDto) {

        returnContactIfExists(contactId);

        return contactRepository.save(contactMapper.contactRequestDtoToContact(contactId, contactRequestDto));
    }

    @Override
    public void deleteContactById(Long contactId) {

        returnContactIfExists(contactId);

        contactRepository.deleteById(contactId);
    }

    //
//    //    veterinarian
//    @Override
//    @Transactional
//    public Veterinarian saveContactToVeterinarianByID(Long veterinarianId, ContactRequestDto contactRequestDto) {
//
//        Veterinarian veterinarian = returnVeterinarianIfExists(veterinarianId);
//
//        if (Objects.nonNull(veterinarian.getContact())) {
//
//            logger.warn("{}, {}!",
//                    "An exception occurred!", "Contact for " + contactRequestDto + " already exists.");
//
//            throw new IllegalArgumentException("Contact for " + contactRequestDto + " already exists.");
//        }
//
//        Contact contact = contactMapper.contactRequestDtoToContact(contactRequestDto);
//
//        veterinarian.setContact(contact);
//
//        return veterinarianService.saveVeterinarianContact(veterinarian);
//    }
//
//    @Override
//    @Transactional
//    public ContactResponseDto findContactByVeterinarianId(Long veterinarianId) {
//
//        Veterinarian veterinarian = returnVeterinarianIfExists(veterinarianId);
//
//        Contact contact = returnContactIfExists(veterinarian);
//
//        return contactMapper.contactToContactResponseDto(contact);
//    }
//
//    @Override
//    @Transactional
//    public Contact updateContactToVeterinarianById(Long veterinarianId,
//                                                   ContactRequestDto contactRequestDto, Long contactId) {
//
//        Veterinarian veterinarian = returnVeterinarianIfExists(veterinarianId);
//
//        returnContactIfExists(veterinarian);
//
//        Contact contact = contactMapper.contactRequestDtoToContact(contactId, contactRequestDto);
//
//        return contactRepository.save(contact);
//    }
//
//    @Override
//    @Transactional
//    public void deleteContactToVeterinarianById(Long veterinarianId, Long contactId) {
//
//        Veterinarian veterinarian = returnVeterinarianIfExists(veterinarianId);
//
//        Contact contact = returnContactIfExists(veterinarian);
//
//        contactRepository.delete(contact);
//    }
//
//    @Override
//    public Veterinarian returnVeterinarianIfExists(Long veterinarianId) {
//        return veterinarianService.returnVeterinarianIfExists(veterinarianId);
//    }
//
//    @Override
//    public Contact returnContactIfExists(Veterinarian veterinarian) {
//
//        if (Objects.isNull(veterinarian.getContact())) {
//
//            throw new NotFoundException("Contact for VeterinarianId " + veterinarian.getId() + " cannot be found ");
//        }
//
//        Optional<Contact> optionalContact = contactRepository.findById(veterinarian.getContact().getId());
//
//        return optionalContact.get();
//    }
//
////    customer
//
//
//    @Override
//    @Transactional
//    public ContactResponseDto findContactByCustomerId(Long customerId, Long contactId) {
//
//        Customer customer = returnCustomerIfExists(customerId);
//
//        Contact contact = returnContactCustomerIfExists(customer);
//
//        return contactMapper.contactToContactResponseDto(contact);
//    }
//
//    @Override
//    @Transactional
//    public Customer saveContactToCustomerByID(Long customerId, ContactRequestDto contactRequestDto) {
//
//        Customer customer = returnCustomerIfExists(customerId);
//
//        if (Objects.nonNull(customer.getContact())) {
//
//            logger.warn("{}, {}!",
//                    "An exception occurred!", "Contact for " + customer + " already exists.");
//
//            throw new IllegalArgumentException("Contact for " + customer + " already exists.");
//        }
//
//        Contact contact = contactMapper.contactRequestDtoToContact(contactRequestDto);
//
//        customer.setContact(contact);
//
//        return customerService.saveCustomerWithContact(customer);
//    }
//
//    @Override
//    @Transactional
//    public Contact updateContactToCustomerById(Long customerId, ContactRequestDto contactRequestDto, Long contactId) {
//
//        Customer customer = returnCustomerIfExists(customerId);
//
//        returnContactCustomerIfExists(customer);
//
//        Contact contact = contactMapper.contactRequestDtoToContact(contactId, contactRequestDto);
//
//        return contactRepository.save(contact);
//    }
//
//    @Override
//    @Transactional
//    public void deleteContactToCustomerById(Long customerId, Long contactId) {
//
//        Customer customer = returnCustomerIfExists(customerId);
//
//        Contact contact = returnContactCustomerIfExists(customer);
//
//        contactRepository.delete(contact);
//    }
//
//    @Override
//    public Customer returnCustomerIfExists(Long customerId) {
//        return customerService.returnCustomerIfExists(customerId);
//    }
//
//    @Override
//    public Contact returnContactCustomerIfExists(Customer customer) {
//
//        if (Objects.isNull(customer.getContact())) {
//
//            logger.warn("{}, {}!",
//                    "An exception occurred!", "Contact cannot be found.",
//                    new NotFoundException("Contact cannot be found."));
//
//            throw new NotFoundException("Contact for Customer " + customer.getId() + " cannot be found ");
//        }
//
//        Optional<Contact> optionalContact = contactRepository.findById(customer.getContact().getId());
//
//        return optionalContact.get();
//    }
}
