package com.example.demo.repository;

import com.example.demo.bean.ProductBuy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBuyRepository extends JpaRepository<ProductBuy, Integer> {
}
