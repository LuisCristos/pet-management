package io.cristos.petmanagement.models.veterinarian;

import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.enums.Gender;
import io.cristos.petmanagement.models.person.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity(name = "Veterinarian")
@Table(name = "veterinarian")
@SequenceGenerator(name = "id_generator", sequenceName = "id_sequence_veterinarian", allocationSize = 10)
@AttributeOverride(name = "id", column = @Column(name = "veterinarianId"))
public class Veterinarian extends Person {

    @Column(
            name = "speciality",
            nullable = false,
            columnDefinition = "VARCHAR(100)"
    )
    @NotBlank(message = "{validation.notblank.speciality}")
    @Size(min = 2, max = 100, message = "{validation.size.input}")
    private String speciality;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId", referencedColumnName = "contactId")
    private Contact contact;

    public Veterinarian(LocalDate dateOfBirth, int age, Gender gender, String firstName, String lastName) {
        super(dateOfBirth, age, gender, firstName, lastName);
    }

    public Veterinarian() {
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
