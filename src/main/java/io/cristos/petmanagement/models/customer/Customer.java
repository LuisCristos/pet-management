package io.cristos.petmanagement.models.customer;

import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.enums.Gender;
import io.cristos.petmanagement.models.person.Person;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "Customer")
@Table(name = "customer")
@SequenceGenerator(name = "id_generator", sequenceName = "id_sequence_customer", allocationSize = 10)
@AttributeOverride(name = "id", column = @Column(name = "customerId"))
public class Customer extends Person {

    // TO_DO_LC: create list of pets
//    private List<Pet> pets;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contactId", referencedColumnName = "contactId")
    private Contact contact;

    public Customer(LocalDate dateOfBirth, int age, Gender gender, String firstName, String lastName) {
        super(dateOfBirth, age, gender, firstName, lastName);
    }

    public Customer() {
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "contact=" + contact +
                '}';
    }
}
