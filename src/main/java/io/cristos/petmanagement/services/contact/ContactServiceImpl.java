package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.contact.ContactDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.repositories.contact.ContactRepository;
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

        return contactRepository.save(contactMapper.contactDtoToContact(contactDto));
    }

    @Override
    public List<ContactDto> getAllContacts() {

        Collection<Contact> contactCollection = contactRepository.findAll();

        if (contactCollection.isEmpty()) {

            logger.info("getAllContacts(). Retrieved an empty List.");
            return Collections.emptyList();
        }

        return contactMapper.contactListToContactDtoList(contactCollection);
    }

    @Override
    public ContactDto findContactById(Long contactId) {

        Optional<Contact> optionalContact = Optional.ofNullable(contactRepository.findById(contactId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}! An exception occurred!",
                            "findContactById().", "Contact with id: " + contactId + " cannot be found because it does not exist.",
                            new NotFoundException("Contact with id: " + contactId + " cannot be found because it does not exist."));

                    return new NotFoundException("Contact with id: " + contactId + " cannot be found because it does not exist.");
                }));

        return contactMapper.contactToContactDto(optionalContact.get());
    }

    @Override
    public void deleteContactById(Long contactId) {

        final String delete = "deleted";
        final String methodName = "deleteContactById()";
        checkIfContactExistsById(contactId, methodName, delete);

        contactRepository.deleteById(contactId);
    }

    @Override
    public Contact updateContactById(Long contactId, ContactDto contactDto) {

        final String update = "updated";
        final String methodName = "updateContactById()";
        checkIfContactExistsById(contactId, methodName, update);

        return contactRepository.save(contactMapper.contactDtoToContact(contactDto));
    }

    private void checkIfContactExistsById(Long contactId, String methodName, String value) {

        boolean exists = contactRepository.existsById(contactId);

        if (!exists) {

            logger.warn("{}, {}! An exception occurred!",
                    "" + methodName + ".", "Contact with id: " + contactId + " cannot be " + value + " because it does not exist.",
                    new NotFoundException("Contact with id: " + contactId + " cannot be " + value + " because it does not exist."));

            throw new NotFoundException("Contact with id: " + contactId + " cannot be " + value + " because it does not exist.");
        }
    }
}
