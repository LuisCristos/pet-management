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

        Optional<Contact> optionalContact = Optional.ofNullable(contactRepository.findById(contactId))
                .orElseThrow(() -> {
                    logger.warn("{}, {}!",
                            "An exception occurred!", "Contact with id: " + contactId + " cannot be found.",
                            new NotFoundException("Contact with id: " + contactId + " cannot be found."));
                    return new NotFoundException("Contact with id: " + contactId + " cannot be found.");
                });

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





}
