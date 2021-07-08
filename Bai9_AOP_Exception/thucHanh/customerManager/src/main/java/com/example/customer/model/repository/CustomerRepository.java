package com.example.customer.model.repository;

import com.example.customer.model.bean.Customer;
import com.example.customer.model.bean.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {
    @Query(value = "SELECT c from CustomerEntity as c WHERE c.name like ?1")
    Page<Customer> search(String valueSearch, Pageable pageable);

    Iterable<Customer> findAllByProvince(Province province);
}
