package com.visams.visatrackingservice.Repository;

import com.visams.visatrackingservice.Entity.LocationHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationHistoryRepository extends JpaRepository<LocationHistory, Integer> {

    Page<LocationHistory> findByLocationId(Integer locationId, Pageable pageable);

    Page<LocationHistory> findByTouristId(Long touristId, Pageable pageable);

    Page<LocationHistory> findByLocationNameContainingIgnoreCase(String locationName, Pageable pageable);

    Page<LocationHistory> findByLocationType(String locationType, Pageable pageable);

}
