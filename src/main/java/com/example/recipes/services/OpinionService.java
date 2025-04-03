package com.example.recipes.services;

import com.example.recipes.entities.Opinion;
import com.example.recipes.repositories.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionService {

    @Autowired
    private OpinionRepository opinionRepository;

    public Opinion getOpinionById(long id) {
        return opinionRepository.findById(id).get();
    }

    public List<Opinion> getAll() {
        return opinionRepository.findAll();
    }
}
