package io.cristos.petmanagement.models.employee;

import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.person.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "Employee")
@Table(name = "employee")
@SequenceGenerator(name = "id_generator", sequenceName = "id_sequence_employee", allocationSize = 10)
@AttributeOverride(name = "id", column = @Column(name = "employeeId"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Employee extends Person {

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId", referencedColumnName = "contactId")
    private Contact contact;

}

