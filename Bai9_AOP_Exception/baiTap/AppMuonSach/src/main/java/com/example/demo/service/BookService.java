package com.example.demo.service;

import com.example.demo.bean.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<Book> findAll(Pageable pageable);

    void save(Book book);

    Book findById(Integer id);
}
