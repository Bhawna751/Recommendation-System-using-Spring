package com.recommendation.Repository;

import com.recommendation.Models.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteractionRepository extends JpaRepository<Interaction,Long> {
}
