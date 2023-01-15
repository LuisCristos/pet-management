package io.cristos.petmanagement.models.customer;

import io.cristos.petmanagement.models.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity(name = "Customer")
@Table(name = "customer")
@SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence_customer", allocationSize = 10)
public class Customer extends Person {

    // TO_DO_LC: create list of pets
//    private List<Pet> pets;


    public Customer(String firstName, String lastName, LocalDate dateOfBirth) {
        super(firstName, lastName, dateOfBirth);
    }

    public Customer() {
    }


}
