package com.example.customer.model.service.impl;

import com.example.customer.model.bean.Customer;
import com.example.customer.model.repository.CustomerRepository;
import com.example.customer.model.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer finById(int id) {
        return customerRepository.finById(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void edit(Customer customer) {
        customerRepository.edit(customer);
    }

    @Override
    public void delete(int id) {
        customerRepository.delete(id);
    }

    @Override
    public List<Customer> search(String searchValue) {
        return customerRepository.search(searchValue);
    }
}
