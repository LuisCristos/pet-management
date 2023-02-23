package io.cristos.petmanagement.models.customer;

import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.enums.Gender;
import io.cristos.petmanagement.models.person.Person;
import io.cristos.petmanagement.models.pet.Pet;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Customer")
@Table(name = "customer")
@SequenceGenerator(name = "id_generator", sequenceName = "id_sequence_customer", allocationSize = 10)
@AttributeOverride(name = "id", column = @Column(name = "customerId"))
public class Customer extends Person {

    @OneToMany(
            mappedBy = "customer",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Pet> petList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId", referencedColumnName = "contactId")
    private Contact contact;

    public Customer(LocalDate dateOfBirth, int age, Gender gender, String firstName, String lastName) {
        super(dateOfBirth, age, gender, firstName, lastName);
    }

    public Customer() {
    }

    public List<Pet> getPets() {
        return petList;
    }

    public void addPet(Pet pet) {
        this.petList.add(pet);
    }

    public void removePet(Pet pet) {
        this.petList.remove(pet);
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
                "petList=" + petList +
                ", contact=" + contact +
                '}';
    }
}
