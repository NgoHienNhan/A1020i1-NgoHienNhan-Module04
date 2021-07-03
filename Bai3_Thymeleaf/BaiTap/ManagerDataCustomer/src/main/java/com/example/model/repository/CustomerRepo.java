package com.example.model.repository;

import com.example.model.bean.Customer;

import java.util.List;

public interface CustomerRepo {
    List<Customer> findAll();

    void save(Customer customer);

    Customer findById(int id);

    void update(int id, Customer customer);

    void remove(int id);
}
