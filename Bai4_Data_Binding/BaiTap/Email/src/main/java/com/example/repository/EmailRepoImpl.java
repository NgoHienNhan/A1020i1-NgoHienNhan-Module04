package com.example.repository;

import com.example.model.Email;

import java.util.ArrayList;
import java.util.List;

public class EmailRepoImpl implements EmailRepo {
     static List<Email> emailList =new ArrayList<>();
    static {
        emailList.add(new Email("VietNamese",25,true,"Iron man from Earth"));
        emailList.add(new Email("English",25,true,"Iron man from Earth"));
        emailList.add(new Email("Chinese",25,true,"Iron man from Earth"));
    }
    @Override
    public List<Email> showAll() {
        return emailList;
    }

    @Override
    public void addEmail(Email email) {
        emailList.add(email);
    }
}
