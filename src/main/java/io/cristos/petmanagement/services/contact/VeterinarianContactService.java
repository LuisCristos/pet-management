package io.cristos.petmanagement.services.contact;

import io.cristos.petmanagement.dtos.request.contact.ContactRequestDto;
import io.cristos.petmanagement.dtos.response.contact.ContactResponseDto;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.veterinarian.Veterinarian;

public interface VeterinarianContactService {

    Veterinarian saveVeterinarianContactByVeterinarianId(Long veterinarianId, ContactRequestDto contactRequestDto);

    ContactResponseDto findVeterinarianContactByVeterinarianId(Long veterinarianId);

    Contact updateVeterinarianContactByVeterinarianId(Long veterinarianId, ContactRequestDto contactRequestDto, Long contactId);

    void deleteVeterinarianContactByVeterinarianId(Long veterinarianId, Long contactId);

    Veterinarian returnVeterinarianIfExists(Long veterinarianId);

    Contact returnContactIfExists(Veterinarian veterinarian);
}
