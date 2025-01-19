package com.recommendation.controller;

import com.recommendation.Models.Item;
import com.recommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;
    @GetMapping("/{userId}")
    public List<Item> getRecommedations(@PathVariable Long userId){
        return recommendationService.recommedItemsForUser(userId);
    }
}
