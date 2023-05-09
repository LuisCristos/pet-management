package io.cristos.petmanagement.repositories.customer;

import io.cristos.petmanagement.models.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Page<Customer> findAll(Pageable pageable);

    Page<Customer> findByLastNameContainingIgnoreCase(String searchValue, Pageable pageable);

    Page<Customer> findByFirstNameContainingIgnoreCase(String searchValue, Pageable pageable);

    Page<Customer> findByBornAt(LocalDate localDate, PageRequest pageRequest);
}
