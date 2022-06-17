package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Member;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelJPQL {

    private final EntityManager entityManager;
    private final TravelRepository travelRepository;

    public void FindTravelByMember(Account account){
        Query query = entityManager.createQuery("SELECT a FROM Account as a", Account.class);
    }

}
