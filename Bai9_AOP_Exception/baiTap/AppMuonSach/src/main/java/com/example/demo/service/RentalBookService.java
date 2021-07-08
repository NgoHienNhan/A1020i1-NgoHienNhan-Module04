package com.example.demo.service;

import com.example.demo.bean.RentalBook;

public interface RentalBookService {
    RentalBook findById(Integer id);

    void save(RentalBook rentalBook);

    void delete(RentalBook rentalBook);
}
