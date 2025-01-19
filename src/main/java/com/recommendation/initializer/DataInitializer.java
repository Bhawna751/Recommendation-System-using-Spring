package com.recommendation.initializer;

import com.recommendation.Models.Interaction;
import com.recommendation.Models.Item;
import com.recommendation.Models.User;
import com.recommendation.Repository.InteractionRepository;
import com.recommendation.Repository.ItemRepository;
import com.recommendation.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initializeData(UserRepository userRepository, ItemRepository itemRepository, InteractionRepository interactionRepository){
        return args -> {
            User kaitlyn = new User("Kaitlyn");
            User alex = new User("Alex");
            userRepository.save(kaitlyn);
            userRepository.save(alex);
            Item book1 = new Item("Book 1");
            Item book2 = new Item("Book 2");
            itemRepository.save(book1);
            itemRepository.save(book2);
            interactionRepository.save(new Interaction(kaitlyn, book1, 5));
            interactionRepository.save(new Interaction(alex, book1, 4));
            interactionRepository.save(new Interaction(kaitlyn,book2,3));
        };
    }
}
