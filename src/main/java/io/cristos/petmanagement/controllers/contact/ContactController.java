package io.cristos.petmanagement.controllers.contact;


import io.cristos.petmanagement.dtos.contact.ContactDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ContactController {

    ResponseEntity<ContactDto> findContactByVeterinarianId(@PathVariable(name = "veterinarianId")
                                                           @Min(1)
                                                           @NotNull
                                                           Long veterinarianId,
                                                           @PathVariable(name = "contactId")
                                                           @Min(1)
                                                           @NotNull
                                                           Long contactId);

    ResponseEntity<ContactDto> saveContactToVeterinarianByID(@PathVariable(name = "veterinarianId")
                                                             @Min(1)
                                                             @NotNull
                                                             Long veterinarianId,
                                                             @Valid
                                                             @RequestBody ContactDto contactDto);

    ResponseEntity<ContactDto> updateContactToVeterinarianById(@PathVariable(name = "veterinarianId")
                                                               @Min(1)
                                                               @NotNull
                                                               Long veterinarianId,
                                                               @Valid
                                                               @RequestBody ContactDto contactDto,
                                                               @PathVariable(name = "contactId")
                                                               @Min(1)
                                                               @NotNull
                                                               Long contactId);

    ResponseEntity<ContactDto> deleteContactToVeterinarianById(@PathVariable(name = "veterinarianId")
                                                               @Min(1)
                                                               @NotNull
                                                               Long veterinarianId,
                                                               @PathVariable(name = "contactId")
                                                               @Min(1)
                                                               @NotNull
                                                               Long contactId);
}
