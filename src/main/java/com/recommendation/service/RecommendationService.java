package com.recommendation.service;

import com.recommendation.Models.Interaction;
import com.recommendation.Models.Item;
import com.recommendation.Repository.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecommendationService {
    @Autowired
    private InteractionRepository interactionRepository;
    public List<Item> recommedItemsForUser(Long userId){
        List<Interaction> allInteractions = interactionRepository.findAll();
        Map<Long, Map<Long,Double>> userItemMatrix = new HashMap<>();

        allInteractions.forEach(interaction -> {
            userItemMatrix.computeIfAbsent(interaction.getUser().getId(), k -> new HashMap<>())
                    .put(interaction.getItem().getId(), interaction.getRating());
        });

        Map<Long, Double> similarityScores = new HashMap<>();
        Map<Long, Double> currentUserItemRatings = userItemMatrix.get(userId);

        userItemMatrix.forEach((otherUserId, itemRatings) -> {
            if(!otherUserId.equals(userId)){
                double similarity = cosineSimilarity(currentUserItemRatings,itemRatings);
                if(similarity>0) similarityScores.put(otherUserId,similarity);
            }
        });

        return similarityScores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .flatMap(entry->userItemMatrix.get(entry.getKey()).entrySet().stream())
                .filter(entry->!currentUserItemRatings.containsKey(entry.getKey()))
                .map(entry->entry.getKey())
                .distinct()
                .map(itemId->new Item(itemId,"Item Description"))
                .collect(Collectors.toList());
    }
    private double cosineSimilarity(Map<Long, Double>userRatings,Map<Long,Double>otherUserRatings){
        double dotProduct = 0;
        double normA = 0;
        double normB = 0;
        for(Long itemId : userRatings.keySet()){
            dotProduct += userRatings.get(itemId) * otherUserRatings.getOrDefault(itemId,0.0);
            normA += Math.pow(userRatings.get(itemId),2);
            normB += Math.pow(otherUserRatings.getOrDefault(itemId,0.0),2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
