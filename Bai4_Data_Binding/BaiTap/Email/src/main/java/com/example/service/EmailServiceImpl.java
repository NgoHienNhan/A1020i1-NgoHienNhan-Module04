package com.example.service;

import com.example.model.Email;
import com.example.repository.EmailRepo;
import com.example.repository.EmailRepoImpl;

import java.util.List;

public class EmailServiceImpl implements EmailService {
    EmailRepo emailRepo= new EmailRepoImpl();

    @Override
    public List<Email> showAll() {
        return emailRepo.showAll();
    }

    @Override
    public void addEmail(Email email) {
        emailRepo.addEmail(email);
    }
}
