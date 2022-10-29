package com.travelwithme.withtravel.Plan;

import com.travelwithme.withtravel.Spot.Spot;
import com.travelwithme.withtravel.Travel.Travel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class PlanRepository {

    private final EntityManager em;

    public Plan save(Plan plan){
        em.persist(plan);
        return plan;
    }
    
    public Plan findById(Long planId){
        try{
            return em.find(Plan.class, planId);
        }catch (NoResultException e){
            return null;
        }
    }

    public List<Plan> findByTravelAndSpot(Travel travel, Spot spot){
        try{
            return em.createQuery("select p from Plan p where p.spot = :spot and p.travel =: travel", Plan.class)
                    .setParameter("spot", spot)
                    .setParameter("travel", travel)
                    .getResultList();

        }finally {
            return null;
        }

    }

    public Plan findByTravelAndPlanName(Travel travel, String planName){
        try{
            return em.createQuery("select p from Plan p where p.planName = :planName and p.travel =: travel", Plan.class)
                    .setParameter("planName", planName)
                    .setParameter("travel", travel)
                    .getSingleResult();

        }catch(NoResultException e) {
            return null;
        }
    }
}
