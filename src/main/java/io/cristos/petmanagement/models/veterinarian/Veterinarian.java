package io.cristos.petmanagement.models.veterinarian;

import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.person.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "Veterinarian")
@Table(name = "veterinarian")
@SequenceGenerator(name = "id_generator", sequenceName = "id_sequence_veterinarian", allocationSize = 10)
@AttributeOverride(name = "id", column = @Column(name = "veterinarianId"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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

}
