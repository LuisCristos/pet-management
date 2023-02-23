package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.response.contact.ContactResponseDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.repositories.contact.ContactRepository;
import io.cristos.petmanagement.services.customer.CustomerService;
import io.cristos.petmanagement.utilities.mapper.contact.ContactMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerContactServiceImpl implements CustomerContactService {

    private final Logger logger = LoggerFactory.getLogger(CustomerContactServiceImpl.class);

    private final ContactMapper contactMapper;
    private final CustomerService customerService;
    private final ContactRepository contactRepository;

    public CustomerContactServiceImpl(ContactMapper contactMapper, CustomerService customerService,
                                      ContactRepository contactRepository) {
        this.contactMapper = contactMapper;
        this.customerService = customerService;
        this.contactRepository = contactRepository;
    }

    @Override
    @Transactional
    public Customer saveContactToCustomerById(Long customerId, ContactRequestDto contactRequestDto) {

        Customer customer = returnCustomerIfExists(customerId);

        if (Objects.nonNull(customer.getContact())) {

            logger.warn("{}, {}!",
                    "An exception occurred!", "Contact for " + customer + " already exists.");

            throw new IllegalArgumentException("Contact for " + customer + " already exists.");
        }

        Contact contact = contactMapper.contactRequestDtoToContact(contactRequestDto);

        customer.setContact(contact);

        return customerService.saveCustomer(customer);
    }


    @Override
    @Transactional
    public ContactResponseDto findCustomerContactByCustomerId(Long customerId, Long contactId) {

        Customer customer = returnCustomerIfExists(customerId);

        Contact contact = returnContactIfExists(customer);

        return contactMapper.contactToContactResponseDto(contact);
    }

    @Override
    @Transactional
    public Contact updatedCustomerContactByCustomerId(Long customerId, ContactRequestDto contactRequestDto, Long contactId) {

        Customer customer = returnCustomerIfExists(customerId);

        Contact contact = contactMapper.contactRequestDtoToContact(contactId, contactRequestDto);

        customer.setContact(contact);

        return customerService.saveCustomer(customer).getContact();
    }

    @Override
    @Transactional
    public void deleteContactToCustomerById(Long customerId, Long contactId) {

        Customer customer = returnCustomerIfExists(customerId);

        returnContactIfExists(customer);

        customer.setContact(null);

        customerService.saveCustomer(customer);
    }

    @Override
    public Customer returnCustomerIfExists(Long customerId) {
        return customerService.returnCustomerIfExists(customerId);
    }

    @Override
    public Contact returnContactIfExists(Customer customer) {

        boolean isNull = Objects.isNull(customer.getContact());

        if (isNull) {
            logger.warn("{}. {}", "An exception occurred!", "Contact cannot be found.");
            throw new NotFoundException("Contact cannot be found.");
        }

        Optional<Contact> optionalContact = Optional.ofNullable(contactRepository.findById(
                        customer.getContact().getId()))
                .orElseThrow(() -> {
                    logger.warn("{}, {}!",
                            "An exception occurred!", "Contact with id: " +
                                    customer.getContact().getId() + " cannot be found.",
                            new NotFoundException("Contact with id: " +
                                    customer.getContact().getId() + " cannot be found."));
                    return new NotFoundException("Contact with id: " +
                            customer.getContact().getId() + " cannot be found.");
                });

        return optionalContact.get();
    }
}
