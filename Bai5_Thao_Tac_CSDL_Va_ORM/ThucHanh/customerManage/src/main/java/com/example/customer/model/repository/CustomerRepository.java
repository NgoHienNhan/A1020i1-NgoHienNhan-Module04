package com.example.customer.model.repository;

import com.example.customer.model.bean.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();

    Customer finById(int id);

    void save(Customer customer);

    void edit(Customer customer);

    void delete(int id);

    List<Customer> search(String searchValue);
}
