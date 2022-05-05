package com.travelwithme.withtravel.Repository;

import com.travelwithme.withtravel.Place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
