package com.visams.visatrackingservice.Repository;

import com.visams.visatrackingservice.Entity.VisaHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisaHistoryRepository extends JpaRepository<VisaHistory, Integer> {

    Page<VisaHistory> findByHistoryId(Integer historyId, Pageable pageable);

    Page<VisaHistory> findByVisa_visaId(Integer visaId, Pageable pageable);

    Page<VisaHistory> findByTouristId(Long touristId, Pageable pageable);
}
