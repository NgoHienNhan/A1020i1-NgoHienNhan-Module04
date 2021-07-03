package com.example.model.service;

import com.example.model.bean.Customer;
import com.example.model.repository.CustomerRepo;
import com.example.model.repository.CustomerRepoImpl;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerRepo customerRepo = new CustomerRepoImpl();

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public void save(Customer customer) {
        customerRepo.save(customer);
    }

    @Override
    public Customer findById(int id) {
        return customerRepo.findById(id);
    }

    @Override
    public void update(int id, Customer customer) {
        customerRepo.update(id, customer);
    }

    @Override
    public void remove(int id) {
        customerRepo.remove(id);
    }
}
