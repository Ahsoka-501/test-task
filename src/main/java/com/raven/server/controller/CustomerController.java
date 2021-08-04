package com.raven.server.controller;

import com.raven.server.model.Customer;
import com.raven.server.model.Views;
import com.raven.server.repository.CustomerRepository;
import com.raven.server.exception.IllegalValueException;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    @JsonView(Views.MainView.class)
    public List<Customer> list() {
        return customerRepository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.MainView.class)
    public Customer getByID(@PathVariable("id") Customer customer) {
        return customer;
    }

    @PostMapping
    @JsonView(Views.MainView.class)
    public Customer add(@RequestBody Customer customer) { // может, возвращать ResponseEntity<String>?
        validatePhone(customer.getPhone(), false);
        customer.setCreated(Instant.now().toEpochMilli());
        customer.setActive(true);
        return customerRepository.save(customer);
    }

    @PutMapping("{id}")
    @JsonView(Views.MainView.class)
    public Customer putUpdate(@PathVariable("id") Customer customerFromDb, @RequestBody Customer newCustomer) {
        return update(customerFromDb, newCustomer);
    }

    @PatchMapping("{id}")
    @JsonView(Views.MainView.class)
    public Customer pathUpdate(@PathVariable("id") Customer customerFromDb, @RequestBody Customer newCustomer) {
        return update(customerFromDb, newCustomer);
    }

    @DeleteMapping("{id}")
    public void deactivate(@PathVariable("id") Customer customerFromDb) {
        customerFromDb.setActive(false);
        customerRepository.save(customerFromDb);
    }

    private Customer update(Customer oldCustomer, Customer newCustomer) {
        validatePhone(newCustomer.getPhone(), true);
        BeanUtils.copyProperties(newCustomer, oldCustomer, "id", "created", "updated", "active");
        oldCustomer.setUpdated(Instant.now().toEpochMilli());
        return customerRepository.save(oldCustomer);
    }

    private void validatePhone(String phone, boolean phoneRequired) {
        if ((phoneRequired && phone == null) || phone != null && !phone.matches("^\\+?[0-9]{6,14}$")) {
            throw new IllegalValueException();
        }
    }
}
