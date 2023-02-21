package io.cristos.petmanagement.controllers.contact;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.response.contact.ContactResponseDto;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.services.contact.ContactService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Validated
public class ContactController {

    private final Logger logger = LoggerFactory.getLogger(ContactController.class);
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // Contact
    @PostMapping("/contacts")
    public ResponseEntity<ContactResponseDto> saveContact(@RequestBody
                                                          @Valid
                                                          ContactRequestDto contactRequestDto) {

        Contact contact = contactService.saveContact(contactRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/contactId")
                .buildAndExpand(contact.getId())
                .toUri();

        logger.info("Saved contact to database.");

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/contacts/{contactId}")
    public ResponseEntity<ContactResponseDto> findContactById(@PathVariable(name = "contactId")
                                                              @Min(value = 1, message = "{validation.min.pathvariable}")
                                                              Long contactId) {

        logger.info("Find contact with id: " + contactId);

        return ResponseEntity.ok(contactService.findContactById(contactId));
    }

    @GetMapping("/contacts")
    public ResponseEntity<List<ContactResponseDto>> getAllContacts() {

        logger.info("Retrieved all contacts.");

        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @PutMapping("/contacts/{contactId}")
    public ResponseEntity<ContactResponseDto> updateContactById(@PathVariable(name = "contactId")
                                                                @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                Long contactId,
                                                                @RequestBody
                                                                @Valid
                                                                ContactRequestDto contactRequestDto) {

        contactService.updateContactById(contactId, contactRequestDto);

        logger.info("Updated contact with id " + contactId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<ContactResponseDto> deleteContactById(@PathVariable(name = "contactId")
                                                                @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                Long contactId) {

        contactService.deleteContactById(contactId);

        logger.info("Deleted contact with id: " + contactId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


//    // veterinarian
//    @PostMapping("/veterinarians/{veterinarianId}/contacts")
//    public ResponseEntity<ContactResponseDto> saveContactToVeterinarianByID(@PathVariable(name = "veterinarianId")
//                                                                            @Min(value = 1, message = "{validation.min.pathvariable}")
//                                                                            Long veterinarianId,
//                                                                            @Valid
//                                                                            @RequestBody ContactRequestDto contactRequestDto) {
//
//        Veterinarian veterinarian = contactService.saveContactToVeterinarianByID(veterinarianId, contactRequestDto);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{contactId}")
//                .buildAndExpand(veterinarian.getContact().getId())
//                .toUri();
//
//        logger.info("Saved contact for veterinarian with id " + veterinarianId);
//
//        return ResponseEntity.created(location).build();
//    }
//
//    @GetMapping("/veterinarians/{veterinarianId}/contacts")
//    public ResponseEntity<ContactResponseDto> findContactByVeterinarianId(@PathVariable(name = "veterinarianId")
//                                                                          @Min(value = 1, message = "{validation.min.pathvariable}")
//                                                                          Long veterinarianId) {
//
//        logger.info("Find contact for veterinarian with id: " + veterinarianId);
//
//        return ResponseEntity.ok(contactService.findContactByVeterinarianId(veterinarianId));
//    }
//
//    @PutMapping("/veterinarians/{veterinarianId}/contacts/{contactId}")
//    public ResponseEntity<ContactResponseDto> updateContactToVeterinarianById(@PathVariable(name = "veterinarianId")
//                                                                              @Min(value = 1, message = "{validation.min.pathvariable}")
//                                                                              Long veterinarianId,
//                                                                              @Valid
//                                                                              @RequestBody ContactRequestDto contactRequestDto,
//                                                                              @PathVariable(name = "contactId")
//                                                                              @Min(value = 1, message = "{validation.min.pathvariable}")
//                                                                              Long contactId) {
//
//        contactService.updateContactToVeterinarianById(veterinarianId, contactRequestDto, contactId);
//
//        logger.info("Updated contact for veterinarian with id " + veterinarianId);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/veterinarians/{veterinarianId}/contacts/{contactId}")
//    public ResponseEntity<ContactResponseDto> deleteContactToVeterinarianById(@PathVariable(name = "veterinarianId")
//                                                                              @Min(value = 1, message = "{validation.min.pathvariable}")
//                                                                              Long veterinarianId,
//                                                                              @PathVariable(name = "contactId")
//                                                                              @Min(value = 1, message = "{validation.min.pathvariable}")
//                                                                              Long contactId) {
//
//
//        contactService.deleteContactToVeterinarianById(veterinarianId, contactId);
//
//        logger.info("Deleted contact for veterinarian with id " + veterinarianId);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
////    customer ******************************
//
//    @GetMapping("/customers/{customerId}/contacts/{contactId}")
//    public ResponseEntity<ContactResponseDto> findContactByCustomerId(@PathVariable(name = "customerId")
//                                                                      @Min(value = 1, message = "{validation.min.pathvariable}")
//                                                                      Long customerId,
//                                                                      @PathVariable(name = "contactId")
//                                                                      @Min(value = 1, message = "{validation.min.pathvariable}")
//                                                                      Long contactId) {
//
//        logger.info("Find contact for customer with id: " + customerId);
//
//        return ResponseEntity.ok(contactService.findContactByCustomerId(customerId, contactId));
//    }
//
//    @PostMapping("/customers/{customerId}/contacts")
//    public ResponseEntity<ContactResponseDto> saveContactToCustomerByID(@PathVariable(name = "customerId")
//                                                                        @Min(value = 1, message = "{validation.min.pathvariable}")
//                                                                        Long customerId,
//                                                                        @Valid
//                                                                        @RequestBody ContactRequestDto contactRequestDto) {
//
//        Customer customer = contactService.saveContactToCustomerByID(customerId, contactRequestDto);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{contactId}")
//                .buildAndExpand(customer.getContact().getId())
//                .toUri();
//
//        logger.info("Saved contact for customer with id " + customerId);
//
//        return ResponseEntity.created(location).build();
//    }
//
//    @PutMapping("/customers/{customerId}/contacts/{contactId}")
//    public ResponseEntity<ContactResponseDto> updateContactToCustomerById(@PathVariable(name = "customerId")
//                                                                          @Min(value = 1, message = "{validation.min.pathvariable}")
//                                                                          Long customerId,
//                                                                          @Valid
//                                                                          @RequestBody ContactRequestDto contactRequestDto,
//                                                                          @PathVariable(name = "contactId")
//                                                                          @Min(value = 1, message = "{validation.min.pathvariable}")
//                                                                          Long contactId) {
//
//        contactService.updateContactToCustomerById(customerId, contactRequestDto, contactId);
//
//        logger.info("Updated contact with contactId: " + contactId);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/customers/{customerId}/contacts/{contactId}")
//    public ResponseEntity<ContactResponseDto> deleteContactToCustomerById(@PathVariable(name = "customerId")
//                                                                          @Min(value = 1, message = "{validation.min.pathvariable}")
//                                                                          Long customerId,
//                                                                          @PathVariable(name = "contactId")
//                                                                          @Min(value = 1, message = "{validation.min.pathvariable}")
//                                                                          Long contactId) {
//
//        contactService.deleteContactToCustomerById(customerId, contactId);
//
//        logger.info("Deleted contact with contactId: " + contactId);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
