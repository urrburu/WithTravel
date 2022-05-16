package com.travelwithme.withtravel.Repository;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Travel.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional(readOnly = true)
public interface TravelRepository extends JpaRepository<Travel, Long> {

    Travel findByMembers(Account account);

    Travel findByTravelName(String travelName);
}
