package com.example.customer.model.service;

import com.example.customer.model.bean.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    Customer findById(Integer id);

    void save(Customer customer);

    void remove(Integer id);

    void update(Customer customer);

    Page<Customer> search(String valueSearch,Pageable pageable);
}
