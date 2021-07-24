package com.example.demo.repository;

import com.example.demo.bean.Smartphone;
import org.springframework.data.repository.CrudRepository;

public interface SmartphoneRepository extends CrudRepository<Smartphone, Integer> {
}
