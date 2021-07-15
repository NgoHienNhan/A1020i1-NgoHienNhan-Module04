package com.example.demo.service;

import com.example.demo.bean.ProductBuy;
import com.example.demo.repository.ProductBuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductBuyServiceImpl implements ProductBuyService{
    @Autowired
    private ProductBuyRepository productBuyRepository;

    @Override
    public void save(ProductBuy productBuy) {
        productBuyRepository.save(productBuy);
    }
}
