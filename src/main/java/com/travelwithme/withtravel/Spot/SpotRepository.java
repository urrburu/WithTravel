package com.travelwithme.withtravel.Spot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface SpotRepository extends JpaRepository<Spot, Long> {

    Spot findBySpotName(String spotName);

    List<Spot> findBySpotNameContaining(String spotName);
}
