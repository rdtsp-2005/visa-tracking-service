package com.visams.visatrackingservice.Repository;

import com.visams.visatrackingservice.Entity.Visa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisaRepository extends JpaRepository<Visa, Integer> {

    Page<Visa> findByVisaId(Integer visaId, Pageable pageable);

    Page<Visa> findByTouristId(Long touristId, Pageable pageable);

    Page<Visa> findByVisaTypeContainingIgnoreCase(String visaType, Pageable pageable);
}
