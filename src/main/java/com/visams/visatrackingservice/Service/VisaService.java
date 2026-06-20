package com.visams.visatrackingservice.Service;

import com.visams.visatrackingservice.Dto.VisaDto;
import com.visams.visatrackingservice.Entity.Visa;
import com.visams.visatrackingservice.Repository.VisaRepository;
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
public class VisaService {

    @Autowired
    private VisaRepository visaRepository;

    private VisaDto toDto(Visa visa){
        return new VisaDto(
                visa.getVisaId(),
                visa.getTouristId(),
                visa.getVisaType(),
                visa.getIssueDate(),
                visa.getExpiryDate(),
                visa.getStatus());
    }

    // view all
    public List<VisaDto> getAllVisas(){
        return visaRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // view by id
    public VisaDto getVisaById(Integer visaId){
        Visa visa = visaRepository.findById(visaId)
                .orElseThrow(() -> new RuntimeException("Visa Not Found"+visaId));
        return toDto(visa);
    }

    // create
    public VisaDto createVisa(VisaDto dto){
        Visa visa = new Visa();
        visa.setTouristId(dto.getTouristId());
        visa.setVisaType(dto.getVisaType());
        visa.setIssueDate(dto.getIssueDate());
        visa.setExpiryDate(dto.getExpiryDate());
        visa.setStatus(dto.getStatus());
        return toDto(visaRepository.save(visa));
    }

    // update
    public VisaDto updateVisa(Integer id, VisaDto updated){
        Visa existing = visaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visa Not Found"+id));

        existing.setVisaId(updated.getVisaId());
        existing.setTouristId(updated.getTouristId());
        existing.setVisaType(updated.getVisaType());
        existing.setIssueDate(updated.getIssueDate());
        existing.setExpiryDate(updated.getExpiryDate());
        existing.setStatus(updated.getStatus());

        return toDto(visaRepository.save(existing));
    }

    // partially update
    public VisaDto partialUpdateVisa(Integer id, Map<String, Object> fields){
        Visa existing = visaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visa not found:"+id));

        if(fields.containsKey("touristId")){
            existing.setTouristId(Long.valueOf(fields.get("touristId").toString()));
        }
        if(fields.containsKey("visaType")){
            existing.setVisaType((String) fields.get("visaType"));
        }
        if(fields.containsKey("issueDate")){
            existing.setIssueDate((LocalDate) fields.get("issueDate"));
        }
        if(fields.containsKey("expiryDate")){
            existing.setExpiryDate((LocalDate) fields.get("expiryDate"));
        }
        if(fields.containsKey("status")){
            existing.setStatus((String) fields.get("status"));
        }

        return toDto(visaRepository.save(existing));
    }

    // delete
    public void deleteVisa(Integer id){
        visaRepository.deleteById(id);
    }

    // pagination and sorting
    public Page<VisaDto> getPageableAllVisas(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return visaRepository.findAll(pageable).map(this::toDto);
    }

    // filtering
    public Page<VisaDto> searchByVisaId(Integer visaId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return visaRepository.findByVisaId(visaId, pageable).map(this::toDto);
    }

    public Page<VisaDto> searchByTouristId(Long touristId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return visaRepository.findByTouristId(touristId, pageable).map(this::toDto);
    }

    public Page<VisaDto> searchByVisaType(String visaType, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return visaRepository.findByVisaTypeContainingIgnoreCase(visaType, pageable).map(this::toDto);
    }
}