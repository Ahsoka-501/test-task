package com.raven.server.data.repos;

import com.raven.server.data.elements.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
