package io.cristos.petmanagement.models.contact;

import io.cristos.petmanagement.models.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity(name = "Contact")
@Table(name = "contact")
@SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence_contact", allocationSize = 10)
public class Contact extends BaseEntity {

    @NotBlank(message = "Street is required")
    private String street;
    private int houseNumber;
    @NotBlank(message = "City is required")
    private String city;
    private String zipCode;
    @NotBlank(message = "Mobile phone number is required")
    private String mobileNumber;
    private String phoneNumber;
    @NotBlank(message = "Email is required")
    @Email(message = "Please enter email in this format valid@valid.com",
            regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;


}