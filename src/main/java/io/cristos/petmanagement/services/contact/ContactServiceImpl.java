package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.contact.ContactDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;
import io.cristos.petmanagement.repositories.contact.ContactRepository;
import io.cristos.petmanagement.repositories.veterinarian.VeterinarianRepository;
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
    private final VeterinarianRepository veterinarianRepository;
    private final ContactMapper contactMapper;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, VeterinarianRepository veterinarianRepository,
                              ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.veterinarianRepository = veterinarianRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public Contact findContactByVeterinarianId(Long veterinarianId, ContactDto contactDto) {

        final String action = "found";
        final String methodName = "findContactByVeterinarianId";

        Optional<Veterinarian> optionalVeterinarian = Optional.of(veterinarianRepository.findById(veterinarianId))
                .orElseThrow(() -> {
                    logger.warn(
                            "{}, {}! An exception occurred!",
                            methodName + ".", " Veterinarian with id: " + veterinarianId + " cannot be " + action + " because it does not exist.",
                            new NotFoundException("Veterinarian with id: " + veterinarianId + " cannot be " + action + " because it does not exist."));

                    return new NotFoundException("Veterinarian with id: " + veterinarianId + " cannot be " + action + " because it does not exist.");
                });


//        boolean exists = veterinarianRepository.existsById(veterinarianId);
//
//        if (!exists) {
//            logger.warn(
//                    "{}, {}! An exception occurred!",
//                    methodName + ".", " Veterinarian with id: " + veterinarianId + " cannot be " + action + " because it does not exist.",
//                    new NotFoundException("Veterinarian with id: " + veterinarianId + " cannot be " + action + " because it does not exist."));
//
//            throw new NotFoundException("Veterinarian with id: " + veterinarianId + " cannot be " + action + " because it does not exist.");
//        }


        return new Contact();
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

        final String action = "deleted";
        final String methodName = "deleteContactById()";
        checkIfContactExistsById(contactId, methodName, action);

        contactRepository.deleteById(contactId);
    }

    @Override
    public Contact updateContactById(Long contactId, ContactDto contactDto) {

        final String action = "updated";
        final String methodName = "updateContactById()";
        checkIfContactExistsById(contactId, methodName, action);

        return contactRepository.save(contactMapper.contactDtoToContact(contactDto));
    }

    private void checkIfContactExistsById(Long contactId, String methodName, String action) {

        boolean exists = contactRepository.existsById(contactId);

        if (!exists) {

            logger.warn("{}, {}! An exception occurred!",
                    "" + methodName + ".", "Contact with id: " + contactId + " cannot be " + action + " because it does not exist.",
                    new NotFoundException("Contact with id: " + contactId + " cannot be " + action + " because it does not exist."));

            throw new NotFoundException("Contact with id: " + contactId + " cannot be " + action + " because it does not exist.");
        }
    }
}
