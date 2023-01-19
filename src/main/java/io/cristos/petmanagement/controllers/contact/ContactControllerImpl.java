package io.cristos.petmanagement.controllers.contact;

import io.cristos.petmanagement.dtos.contact.ContactDto;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.services.contact.ContactService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/contacts")
public class ContactControllerImpl implements ContactController {

    private final Logger logger = LoggerFactory.getLogger(ContactControllerImpl.class);
    private final ContactService contactService;

    @Autowired
    public ContactControllerImpl(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    @PostMapping
    public ResponseEntity<ContactDto> saveContact(@Valid @RequestBody ContactDto contactDto) {

        Contact contact = contactService.saveContact(contactDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/contactId")
                .buildAndExpand(contact.getId())
                .toUri();

        logger.info(contactDto + " saved in to Database.");

        return ResponseEntity.created(location).build();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ContactDto>> getAllContacts() {

        logger.info("getAllContacts(). Retrieved all contacts.");

        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @Override
    @GetMapping("/{contactId}")
    public ResponseEntity<ContactDto> findContactById(@PathVariable Long contactId) {

        logger.info("Find contact with id: " + contactId);

        return ResponseEntity.ok(contactService.findContactById(contactId));
    }

    @Override
    @DeleteMapping("/{contactId}")
    public ResponseEntity<ContactDto> deleteContactById(@PathVariable final Long contactId) {

        contactService.deleteContactById(contactId);

        logger.info("Deleted Contact with contactId: " + contactId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PutMapping("/{contactId}")
    public ResponseEntity<ContactDto> updateContactById(@PathVariable Long contactId,
                                                        @Valid @RequestBody ContactDto contactDto) {
        contactService.updateContact(contactId, contactDto);

        logger.info("Updated Contact with contactId: " + contactId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
