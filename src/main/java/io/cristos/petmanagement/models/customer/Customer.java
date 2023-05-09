package io.cristos.petmanagement.models.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.person.Person;
import io.cristos.petmanagement.models.pet.Pet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Customer")
@Table(name = "customer")
@SequenceGenerator(name = "id_generator", sequenceName = "id_sequence_customer", allocationSize = 10)
@AttributeOverride(name = "id", column = @Column(name = "customerId"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Customer extends Person {

    @OneToMany(
            mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private List<Pet> petList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId", referencedColumnName = "contactId")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Contact contact;

    public List<Pet> getPets() {
        return petList;
    }

    public void addPet(Pet pet) {
        this.petList.add(pet);
        pet.setCustomer(this);
    }

    public void removePet(Pet pet) {
        this.petList.remove(pet);
        pet.setCustomer(null);
    }
}
