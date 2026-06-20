package com.visams.visatrackingservice.Service;

import com.visams.visatrackingservice.Dto.VisaExtensionDto;
import com.visams.visatrackingservice.Entity.Visa;
import com.visams.visatrackingservice.Entity.VisaExtension;
import com.visams.visatrackingservice.Repository.VisaExtensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VisaExtensionService {

    @Autowired
    private VisaExtensionRepository visaExtensionRepository;

    private VisaExtensionDto toDto(VisaExtension visaExtension){
        return new VisaExtensionDto(
                visaExtension.getExtensionId(),
                visaExtension.getVisa().getVisaId(),
                visaExtension.getExtendedDate(),
                visaExtension.getReason());
    }

    // view all
    public List<VisaExtensionDto> getAllVisaExtensions(){
        return visaExtensionRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // view by id
    public VisaExtensionDto getVisaExtensionById(Integer id){
        VisaExtension visaExtension = visaExtensionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visa Extension Not Found"));
        return toDto(visaExtension);
    }

    // create new one
    public VisaExtensionDto createVisaExtension(VisaExtensionDto dto){
        VisaExtension visaExtension = new VisaExtension();

        Visa visa = new Visa();
        visa.setVisaId(dto.getVisaId());
        visaExtension.setVisa(visa);

        visaExtension.setExtendedDate(dto.getExtendedDate());
        visaExtension.setReason(dto.getReason());
        return toDto(visaExtensionRepository.save(visaExtension));
    }

    // update
    public VisaExtensionDto updateVisaExtension(Integer id, VisaExtensionDto updated){
        VisaExtension existing = visaExtensionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visa Extension Not Found"+id));

        Visa visa = new Visa();
        visa.setVisaId(updated.getVisaId());
        existing.setVisa(visa);

        existing.setExtensionId(updated.getExtensionId());
        existing.setExtendedDate(updated.getExtendedDate());
        existing.setReason(updated.getReason());

        return toDto(visaExtensionRepository.save(existing));
    }

    // partially update
    public VisaExtensionDto partialUpdateVisaExtension(Integer id, Map<String,Object> fields){
        VisaExtension existing = visaExtensionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visa Extension Not Found"+id));

        if(fields.containsKey("visaId")){
            Visa visa = new Visa();
            visa.setVisaId(Integer.valueOf(fields.get("visaId").toString()));
            existing.setVisa(visa);
        }
        if(fields.containsKey("extendedDate")){
            existing.setExtendedDate((LocalDate) fields.get("extendedDate"));
        }
        if(fields.containsKey("reason")){
            existing.setReason((String) fields.get("reason"));
        }

        return toDto(visaExtensionRepository.save(existing));
    }

    // delete
    public void deleteVisaExtension(Integer id){
        visaExtensionRepository.deleteById(id);
    }

    // pageable and sorting
    public Page<VisaExtensionDto> getPageableAllVisaExtensions(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return visaExtensionRepository.findAll(pageable).map(this::toDto);
    }

    // filtering
    public Page<VisaExtensionDto> searchByExtensionId(Integer extensionId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return visaExtensionRepository.findByExtensionId(extensionId, pageable).map(this::toDto);
    }

    public Page<VisaExtensionDto> searchByVisaId(Integer visaId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return visaExtensionRepository.findByVisa_visaId(visaId, pageable).map(this::toDto);
    }
}