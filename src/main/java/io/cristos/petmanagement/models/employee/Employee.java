package io.cristos.petmanagement.models.employee;

import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.enums.Gender;
import io.cristos.petmanagement.models.person.Person;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "Employee")
@Table(name = "employee")
@SequenceGenerator(name = "id_generator", sequenceName = "id_sequence_employee", allocationSize = 10)
@AttributeOverride(name = "id", column = @Column(name = "employeeId"))
public class Employee extends Person {

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId", referencedColumnName = "contactId")
    private Contact contact;

    public Employee(LocalDate dateOfBirth, int age, Gender gender, String firstName, String lastName) {
        super(dateOfBirth, age, gender, firstName, lastName);
    }

    public Employee() {
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "contact=" + contact +
                '}';
    }
}

