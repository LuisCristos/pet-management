package io.cristos.petmanagement.models.pet;

import io.cristos.petmanagement.models.BaseEntity;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.models.diagnosis.Diagnosis;
import io.cristos.petmanagement.models.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Pet")
@Table(name = "pet")
@SequenceGenerator(name = "id_generator", sequenceName = "id_sequence_pet", allocationSize = 10)
@AttributeOverride(name = "id", column = @Column(name = "petId"))
public class Pet extends BaseEntity {

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    @NotBlank(message = "{validation.notblank.name}")
    @Size(min = 2, max = 255, message = "{validation.size.input}")
    private String name;
    @OneToMany(
            mappedBy = "pet",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Diagnosis> diagnosisList = new ArrayList<>();
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            }
    )
    private Customer customer;

    public Pet(LocalDate dateOfBirth, int age, Gender gender, String name, List<Diagnosis> diagnosisList) {
        super(dateOfBirth, age, gender);
        this.name = name;
        this.diagnosisList = diagnosisList;
    }

    public Pet(String name, List<Diagnosis> diagnosisList) {
        this.name = name;
        this.diagnosisList = diagnosisList;
    }

    public Pet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Diagnosis> getDiagnosisList() {
        return diagnosisList;
    }

    public void addDiagnosis(Diagnosis diagnosis) {
        this.diagnosisList.add(diagnosis);
    }

    public void removeDiagnosis(Diagnosis diagnosis) {
        this.diagnosisList.remove(diagnosis);
    }

//    public Customer getCustomer() {
//        return customer;
//    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", diagnosisList=" + diagnosisList +
                ", customer=" + customer +
                '}';
    }
}
