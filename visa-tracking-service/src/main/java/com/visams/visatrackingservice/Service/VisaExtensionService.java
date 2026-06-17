package com.visams.visatrackingservice.Service;

import com.visams.visatrackingservice.Entity.VisaExtension;
import com.visams.visatrackingservice.Repository.VisaExtensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class VisaExtensionService {

    @Autowired
    private VisaExtensionRepository visaExtensionRepository;

    public List<VisaExtension> getAllVisaExtensions(){
        return visaExtensionRepository.findAll();
    }

    public VisaExtension getVisaExtensionById(Integer id){
        return visaExtensionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visa Extension Not Found"));
    }

    public VisaExtension createVisaExtension(VisaExtension visaExtension){
        return visaExtensionRepository.save(visaExtension);
    }

    public VisaExtension updateVisaExtension(Integer id, VisaExtension updated){
        VisaExtension existing = visaExtensionRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Visa Extension Not Found"+id));

        existing.setExtensionId(updated.getExtensionId());
        existing.setVisa(updated.getVisa());
        existing.setExtendedDate(updated.getExtendedDate());
        existing.setReason(updated.getReason());

        return visaExtensionRepository.save(existing);
    }

    public VisaExtension partialUpdateVisaExtension(Integer id, Map<String,Object> fields){
        VisaExtension existing = visaExtensionRepository.findById(id).orElseThrow(() -> new RuntimeException("Visa Extension Not Found"+id));

        if(fields.containsKey("extendedDate")){
            existing.setExtendedDate((LocalDate) fields.get("extendedDate"));
        }

        if(fields.containsKey("reason")){
            existing.setReason((String) fields.get("reason"));
        }

        return visaExtensionRepository.save(existing);
    }

    public void deleteVisaExtension(Integer id){
        visaExtensionRepository.deleteById(id);
    }
}
