package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.contact.ContactDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.repositories.ContactRepository;
import io.cristos.petmanagement.utilities.mapper.contact.ContactMapper;
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
    private final ContactMapper contactMapper;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public Contact saveContact(ContactDto contactDto) {

        logger.info(contactDto + " saved in to Database.");

        return contactRepository.save(contactMapper.contactDtoToContact(contactDto));
    }

    @Override
    public List<ContactDto> getAllContacts() {

        Collection<Contact> contactCollection = contactRepository.findAll();

        if (contactCollection.isEmpty()) {

            logger.info("getAllContacts(). Retrieved an empty List.");
            return Collections.emptyList();
        }

        logger.info("getAllContacts(). Retrieved all Contacts.");

        return contactMapper.contactListToContactDtoList(contactCollection);
    }

    @Override
    public ContactDto findContactById(Long contactId) {

        Optional<Contact> optionalContact = contactRepository.findById(contactId);

        if (optionalContact.isEmpty()) {

            logger.warn("{}, {}! An exception occurred!",
                    "findContactById().", "Contact with id: " + contactId + " cannot be found because it does not exist.",
                    new NotFoundException("Contact with id: " + contactId + " not found"));

            throw new NotFoundException("Contact with id: " + contactId + " cannot be found.");
        }

        logger.info("Retrieved contact with id: " + contactId);

        return contactMapper.contactToContactDto(optionalContact.get());
    }

    @Override
    public void deleteContactById(Long contactId) {

        boolean exists = contactRepository.existsById(contactId);

        if (!exists) {

            logger.warn("{}, {}! An exception occurred!",
                    "deleteContactById().", "Contact with id: " + contactId + " cannot be deleted because it does not exist.",
                    new NotFoundException("Contact with id: " + contactId + " not found"));

            throw new NotFoundException("Contact ID: " + contactId + " cannot be deleted because it does not exist.");
        }

        logger.info("Deleted Contact with id: " + contactId);

        contactRepository.deleteById(contactId);

    }

    @Override
    public Contact updateContact(Long contactId, ContactDto contactDto) {

        boolean exists = contactRepository.existsById(contactId);

        if (!exists) {

            logger.warn("{}, {}! An exception occurred!",
                    "updateContact().", "Contact with id: " + contactId + " cannot be updated because it does not exist.",
                    new NotFoundException("Contact with id: " + contactId + " not found"));

            throw new NotFoundException("Contact ID: " + contactId + " cannot be updated because it does not exist.");
        }

        return contactRepository.save(contactMapper.contactDtoToContact(contactDto));
    }
}
