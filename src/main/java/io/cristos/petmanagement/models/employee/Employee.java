package io.cristos.petmanagement.models.employee;

import io.cristos.petmanagement.models.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity(name = "Employee")
@Table(name = "employee")
@SequenceGenerator(name = "id_generator", sequenceName = "id_sequence_employee", allocationSize = 10)
public class Employee extends Person {

    public Employee(String firstName, String lastName, LocalDate dateOfBirth) {
        super(firstName, lastName, dateOfBirth);
    }

    public Employee() {
    }


}
