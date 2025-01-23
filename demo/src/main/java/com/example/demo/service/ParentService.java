package com.example.demo.service;

import com.example.demo.repository.ParentRepository;
import org.springframework.stereotype.Service;

@Service
public class ParentService {

    private final ParentRepository parentRepository;
    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public void testFetchJoin(){

    }
}
