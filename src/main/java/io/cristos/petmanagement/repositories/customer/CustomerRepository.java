package io.cristos.petmanagement.repositories.customer;

import io.cristos.petmanagement.models.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
