package io.cristos.petmanagement.controllers.contact;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.response.contact.ContactResponseDto;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.services.contact.ContactService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/customers")
public class CustomerContactController {

    private final Logger logger = LoggerFactory.getLogger(CustomerContactController.class);

    private final ContactService contactService;

    @Autowired
    public CustomerContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/{customerId}/contacts/{contactId}")
    public ResponseEntity<ContactResponseDto> findCustomerContactByCustomerId(@PathVariable(name = "customerId")
                                                                              @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                              Long customerId,
                                                                              @PathVariable(name = "contactId")
                                                                              @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                              Long contactId) {

        logger.info("Find contact for customer with id: " + customerId);

        return ResponseEntity.ok(contactService.findCustomerContactByCustomerId(customerId, contactId));
    }

    @PostMapping("/{customerId}/contacts")
    public ResponseEntity<ContactResponseDto> saveContactToCustomerByID(@PathVariable(name = "customerId")
                                                                        @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                        Long customerId,
                                                                        @Valid
                                                                        @RequestBody ContactRequestDto contactRequestDto) {

        Customer customer = contactService.saveContactToCustomerById(customerId, contactRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{contactId}")
                .buildAndExpand(customer.getContact().getId())
                .toUri();

        logger.info("Saved contact for customer with id " + customerId);

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{customerId}/contacts/{contactId}")
    public ResponseEntity<ContactResponseDto> updatedCustomerContactByCustomerId(@PathVariable(name = "customerId")
                                                                                 @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                                 Long customerId,
                                                                                 @Valid
                                                                                 @RequestBody ContactRequestDto contactRequestDto,
                                                                                 @PathVariable(name = "contactId")
                                                                                 @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                                 Long contactId) {

        contactService.updatedCustomerContactByCustomerId(customerId, contactRequestDto, contactId);

        logger.info("Updated contact for customer with contactId: " + contactId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}/contacts/{contactId}")
    public ResponseEntity<ContactResponseDto> deleteCustomerContactByCustomerId(@PathVariable(name = "customerId")
                                                                                @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                                Long customerId,
                                                                                @PathVariable(name = "contactId")
                                                                                @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                                Long contactId) {

        contactService.deleteContactToCustomerById(customerId, contactId);

        logger.info("Deleted contact with contactId: " + contactId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
