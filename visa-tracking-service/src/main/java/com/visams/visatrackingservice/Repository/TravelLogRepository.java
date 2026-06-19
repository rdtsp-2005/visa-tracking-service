package com.visams.visatrackingservice.Repository;

import com.visams.visatrackingservice.Entity.TravelLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelLogRepository extends JpaRepository<TravelLog, Integer> {

    Page<TravelLog> findByLogId(Integer logId, Pageable pageable);

    Page<TravelLog> findByTouristId(Long touristId, Pageable pageable);

    Page<TravelLog> findByLocationContainingIgnoreCase(String location, Pageable pageable);
}
