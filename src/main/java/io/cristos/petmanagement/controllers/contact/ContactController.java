package io.cristos.petmanagement.controllers.contact;


import io.cristos.petmanagement.dtos.contact.ContactDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ContactController {

    ResponseEntity<ContactDto> findContactByVeterinarianId(@PathVariable(name = "veterinarianId")
                                                           @Min(1)
                                                           @NotNull
                                                           Long veterinarianId,
                                                           @Valid
                                                           @RequestBody ContactDto contactDto);


    ResponseEntity<ContactDto> saveContact(@Valid
                                           @RequestBody ContactDto contactDto);

    ResponseEntity<List<ContactDto>> getAllContacts();

    ResponseEntity<ContactDto> findContactById(@PathVariable(name = "contactId")
                                               @NotNull
                                               @Min(1) Long contactId);

    ResponseEntity<ContactDto> deleteContactById(@PathVariable(name = "contactId")
                                                 @NotNull
                                                 @Min(1) Long contactId);

    ResponseEntity<ContactDto> updateContactById(@PathVariable(name = "contactId")
                                                 @NotNull
                                                 @Min(1) Long contactId,
                                                 @Valid
                                                 @RequestBody ContactDto contactDto);
}
