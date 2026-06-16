package com.visams.visatrackingservice.Service;

import com.visams.visatrackingservice.Entity.Visa;
import com.visams.visatrackingservice.Repository.VisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class VisaService {

    @Autowired
    private VisaRepository visaRepository;

    public List<Visa> getAllVisas(){
        return visaRepository.findAll();
    }

    public Visa getVisaById(Integer visaId){
        return visaRepository.findById(visaId)
                .orElseThrow(() -> new RuntimeException("Visa Not Found"+visaId));
    }

    public Visa createVisa(Visa visa){
        return visaRepository.save(visa);
    }

    public Visa updateVisa(Integer id, Visa updated){
        Visa existing = visaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visa Not Found"+id));

        existing.setVisaId(updated.getVisaId());
        existing.setTouristId(updated.getTouristId());
        existing.setVisaType(updated.getVisaType());
        existing.setIssueDate(updated.getIssueDate());
        existing.setExpiryDate(updated.getExpiryDate());
        existing.setStatus(updated.getStatus());

        return visaRepository.save(existing);
    }

    public void deleteVisa(Integer id){
        visaRepository.deleteById(id);
    }

}
