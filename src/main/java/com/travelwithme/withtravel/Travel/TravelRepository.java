package com.travelwithme.withtravel.Travel;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.ProxyUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



@Repository
@RequiredArgsConstructor
@Transactional
public class TravelRepository{


    private final EntityManager entityManager;

    public Travel save(Travel travel){entityManager.persist(travel);return travel;}

    public Travel findById(Long id){ return entityManager.find(Travel.class, id);}

    public Travel findByPath(String travelPath) {
            try{
            return entityManager.createQuery("select t from Travel t where t.path = :path", Travel.class)
                    .setParameter("path", travelPath)
                    .getSingleResult();
            }catch (NoResultException e){
                return null;
            }
    }

    public List<Travel> findAll() {
        return entityManager.createQuery("select t from Travel  t", Travel.class)
                .getResultList();
    }

    public void deleteAll(){
        List<Travel> travelList = findAll();
        for(Travel T: travelList){
            entityManager.remove(T);
        }
    }
}