package com.visams.visatrackingservice.Repository;

import com.visams.visatrackingservice.Entity.TravelLog;
import com.visams.visatrackingservice.Entity.Visa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelLogRepository extends JpaRepository<TravelLog,Integer> {
}
