package com.travelwithme.withtravel.Spot;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class SpotRepository {
    EntityManagerFactory entityManagerFactory;

    @PersistenceContext
    EntityManager entityManager;

    public Spot save(Spot spot){
        if(entityManager.contains(spot))return null;
        entityManager.persist(spot);
        return spot;
    }

    public Spot findBySpotName(String spotName) {
        return entityManager.find(Spot.class, spotName);
    }
}
