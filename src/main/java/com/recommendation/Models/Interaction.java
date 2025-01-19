package com.recommendation.Models;

import jakarta.persistence.*;

@Entity
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    private double rating;

    public Interaction(User user, Item item,double rating) {

        this.user = user;
        this.rating = rating;
        this.item=item;
    }

    public Item getItem(){
        return item;
    }

    public void setItem(Item item){
        this.item=item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
