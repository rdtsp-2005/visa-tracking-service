package com.visams.visatrackingservice.Repository;

import com.visams.visatrackingservice.Entity.Visa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisaRepository extends JpaRepository<Visa,Integer> {
}
