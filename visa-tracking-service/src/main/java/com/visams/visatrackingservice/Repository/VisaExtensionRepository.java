package com.visams.visatrackingservice.Repository;


import com.visams.visatrackingservice.Entity.Visa;
import com.visams.visatrackingservice.Entity.VisaExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisaExtensionRepository extends JpaRepository<VisaExtension, Integer> {

    Page<VisaExtension> findByExtensionId(Integer extensionId, Pageable pageable);

    Page<VisaExtension> findByVisa_visaId(Integer visaId, Pageable pageable);

}
