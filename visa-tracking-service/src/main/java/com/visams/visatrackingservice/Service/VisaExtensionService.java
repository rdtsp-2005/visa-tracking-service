package com.visams.visatrackingservice.Service;

import com.visams.visatrackingservice.Entity.VisaExtension;
import com.visams.visatrackingservice.Repository.VisaExtensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        existing.setVisaId(updated.getVisaId());
        existing.setExtendedDate(updated.getExtendedDate());
        existing.setReason(updated.getReason());

        return visaExtensionRepository.save(existing);
    }

    public void deleteVisaExtension(Integer id){
        visaExtensionRepository.deleteById(id);
    }
}
