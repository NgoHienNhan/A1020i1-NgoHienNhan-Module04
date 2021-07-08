package com.example.demo.repository;

import com.example.demo.bean.RentalBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalBookRepository extends JpaRepository<RentalBook,Integer> {
}
