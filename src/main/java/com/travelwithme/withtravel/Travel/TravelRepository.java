package com.travelwithme.withtravel.Travel;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Repository
@RequiredArgsConstructor
public class TravelRepository{

    private final EntityManager entityManager;

    public Travel save(Travel travel){entityManager.persist(travel);return travel;}

    public Travel findById(Long id){ return entityManager.find(Travel.class, id);}

    public Travel findByPath(String travelPath) {
        return entityManager.createQuery("select t from Travel t where t.path = :path", Travel.class)
                .setParameter("path", travelPath)
                .getSingleResult();
    }

    public List<Travel> findAll() {
        return entityManager.createQuery("select t from Travel  t", Travel.class)
                .getResultList();
    }

    public void deleteAll() {
        entityManager.createQuery("delete from Travel  t" );
    }
}