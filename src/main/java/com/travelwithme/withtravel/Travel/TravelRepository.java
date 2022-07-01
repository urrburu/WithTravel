package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Travel.Travel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional(readOnly = true)
public interface TravelRepository extends JpaRepository<Travel, Long> {

    boolean existsByPath(String path);

    Travel findByMembers(Account account);

    Travel findByTravelName(String travelName);

    @EntityGraph(attributePaths = {"tags", "plans", "managers", "members"}, type = EntityGraph.EntityGraphType.LOAD)
    Travel findByPath(String Path);
}
