package com.example.recipes.services;

import com.example.recipes.entities.Opinion;
import com.example.recipes.repositories.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OpinionService {

    @Autowired
    private OpinionRepository opinionRepository;

    public Opinion getOpinionById(long id) {
        return opinionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Opinion not found with id: " + id));
    }

    public List<Opinion> getAllOpinions() {
        return opinionRepository.findAll();
    }

    public List<Opinion> getAllOpinionForRecipeByRecipeId(long recipeId) {
        return opinionRepository.getAllOpinionsByRecipeId(recipeId);
    }

    public void addOpinion(Opinion opinion) {
        opinionRepository.save(opinion);
    }

    public void deleteOpinionById(long id) {
        opinionRepository.delete(getOpinionById(id));
    }
}
