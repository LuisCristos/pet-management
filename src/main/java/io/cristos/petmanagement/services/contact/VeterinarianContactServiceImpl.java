package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.response.contact.ContactResponseDto;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class VeterinarianContactServiceImpl implements VeterinarianContactService {

    private final Logger logger = LoggerFactory.getLogger(VeterinarianContactServiceImpl.class);

    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;
    private final VeterinarianRepository veterinarianRepository;

    @Autowired
    public VeterinarianContactServiceImpl(ContactMapper contactMapper, ContactRepository contactRepository,
                                          VeterinarianRepository veterinarianRepository) {
        this.contactMapper = contactMapper;
        this.contactRepository = contactRepository;
        this.veterinarianRepository = veterinarianRepository;
    }

    @Override
    @Transactional
    public Veterinarian saveVeterinarianContactByVeterinarianId(Long veterinarianId, ContactRequestDto contactRequestDto) {

        Veterinarian veterinarian = returnVeterinarianIfExists(veterinarianId);

        if (Objects.nonNull(veterinarian.getContact())) {

            logger.warn("{}, {}!",
                    "An exception occurred!", "Contact for " + contactRequestDto + " already exists.");

            throw new IllegalArgumentException("Contact for " + contactRequestDto + " already exists.");
        }

        Contact contact = contactMapper.contactRequestDtoToContact(contactRequestDto);

        veterinarian.setContact(contact);

        return veterinarianRepository.save(veterinarian);
    }

    @Override
    @Transactional
    public ContactResponseDto findVeterinarianContactByVeterinarianId(Long veterinarianId) {

        Veterinarian veterinarian = returnVeterinarianIfExists(veterinarianId);

        Contact contact = returnContactIfExists(veterinarian);

        return contactMapper.contactToContactResponseDto(contact);
    }

    @Override
    @Transactional
    public Contact updateVeterinarianContactByVeterinarianId(Long veterinarianId,
                                                             ContactRequestDto contactRequestDto, Long contactId) {

        Veterinarian veterinarian = returnVeterinarianIfExists(veterinarianId);

        returnContactIfExists(veterinarian);

        Contact contact = contactMapper.contactRequestDtoToContact(contactId, contactRequestDto);

        veterinarian.setContact(contact);

        return veterinarianRepository.save(veterinarian).getContact();
    }

    @Override
    @Transactional
    public void deleteVeterinarianContactByVeterinarianId(Long veterinarianId, Long contactId) {

        Veterinarian veterinarian = returnVeterinarianIfExists(veterinarianId);

        returnContactIfExists(veterinarian);

        veterinarian.setContact(null);

        veterinarianRepository.save(veterinarian);
    }

    @Override
    public Veterinarian returnVeterinarianIfExists(Long veterinarianId) {

        Optional<Veterinarian> optionalVeterinarian = Optional.ofNullable(veterinarianRepository.findById(veterinarianId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}!",
                            "An exception occurred!", "Veterinarian with id: " + veterinarianId + " cannot be found.",
                            new NotFoundException("Veterinarian with id: " + veterinarianId + " cannot be found."));

                    throw new NotFoundException("Veterinarian with id: " + veterinarianId + " cannot be found.");
                }));

        return optionalVeterinarian.get();
    }

    @Override
    public Contact returnContactIfExists(Veterinarian veterinarian) {

        boolean isNull = Objects.isNull(veterinarian.getContact());

        if (isNull) {
            logger.warn("{}. {}", "An exception occurred!", "Contact cannot be found.");
            throw new NotFoundException("Contact cannot be found.");
        }

        Optional<Contact> optionalContact = Optional.ofNullable(contactRepository.findById(
                        veterinarian.getContact().getId()))
                .orElseThrow(() -> {
                    logger.warn("{}, {}!",
                            "An exception occurred!", "Contact with id: " +
                                    veterinarian.getContact().getId() + " cannot be found.",
                            new NotFoundException("Contact with id: " +
                                    veterinarian.getContact().getId() + " cannot be found."));
                    return new NotFoundException("Contact with id: " +
                            veterinarian.getContact().getId() + " cannot be found.");
                });

        return optionalContact.get();
    }
}
