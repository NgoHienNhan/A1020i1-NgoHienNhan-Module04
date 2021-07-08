package com.example.customer.model.service;

import com.example.customer.model.bean.Customer;
import com.example.customer.model.bean.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CustomerService {
    Page<Customer> findAllPage(Pageable pageable);

    Iterable<Customer> findAll();

    Iterable<Customer> findAllByProvince(Province province);

    Customer findById(Integer id);

    void save(Customer customer);

    void remove(Integer id);

    void update(Customer customer);

    Page<Customer> search(String valueSearch, Pageable pageable);
}
