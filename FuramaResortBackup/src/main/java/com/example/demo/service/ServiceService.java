package com.example.demo.service;

import com.example.demo.bean.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceService{
    Page<Service> findAll(Pageable pageable);

    Iterable<Service> findAllNotPage();

    Service findById(String id);

    void save(Service service);

    void delete(Service service);
}
