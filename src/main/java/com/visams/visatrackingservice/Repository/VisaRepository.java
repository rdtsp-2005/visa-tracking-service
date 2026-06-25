package com.visams.visatrackingservice.Repository;

import com.visams.visatrackingservice.Entity.Visa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisaRepository extends JpaRepository<Visa, Integer> {

    Page<Visa> findByVisaId(Integer visaId, Pageable pageable);

    Page<Visa> findByPassportId(Long passportId, Pageable pageable);

    Page<Visa> findByVisaTypeContainingIgnoreCase(String visaType, Pageable pageable);

    List<Visa> findByExpiryDateInAndStatus(List<LocalDate> dates, String status);
}
