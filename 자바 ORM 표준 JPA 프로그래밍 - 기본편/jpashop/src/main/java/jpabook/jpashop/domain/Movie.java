package jpabook.jpashop.domain;

import jakarta.persistence.Entity;

@Entity
public class Movie extends Item{

    private String directior;
    private String actor;

    public String getDirectior() {
        return directior;
    }

    public void setDirectior(String directior) {
        this.directior = directior;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
