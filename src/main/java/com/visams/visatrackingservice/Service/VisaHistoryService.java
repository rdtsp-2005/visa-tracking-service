package com.visams.visatrackingservice.Service;

import com.visams.visatrackingservice.Dto.VisaHistoryDto;
import com.visams.visatrackingservice.Entity.Visa;
import com.visams.visatrackingservice.Entity.VisaHistory;
import com.visams.visatrackingservice.Repository.VisaHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VisaHistoryService {

    @Autowired
    private VisaHistoryRepository visaHistoryRepository;

    private VisaHistoryDto toDto(VisaHistory visaHistory){
        return new VisaHistoryDto(
                visaHistory.getHistoryId(),
                visaHistory.getVisa().getVisaId(),
                visaHistory.getTouristId(),
                visaHistory.getAction(),
                visaHistory.getRemarks(),
                visaHistory.getActionDate());
    }

    // view all
    public List<VisaHistoryDto> getAllVisaHistories(){
        return visaHistoryRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // view by id
    public VisaHistoryDto getVisaHistoryById(Integer historyId){
        VisaHistory visaHistory = visaHistoryRepository.findById(historyId)
                .orElseThrow(() -> new RuntimeException("Visa History Not Found"+historyId));
        return toDto(visaHistory);
    }

    // create
    public VisaHistoryDto createVisaHistory(VisaHistoryDto dto){
        VisaHistory visaHistory = new VisaHistory();

        Visa visa = new Visa();
        visa.setVisaId(dto.getVisaId());
        visaHistory.setVisa(visa);

        visaHistory.setTouristId(dto.getTouristId());
        visaHistory.setHistoryId(dto.getHistoryId());
        visaHistory.setAction(dto.getAction());
        visaHistory.setRemarks(dto.getRemarks());
        visaHistory.setActionDate(dto.getActionDate());

        return toDto(visaHistoryRepository.save(visaHistory));
    }

    // update
    public VisaHistoryDto updateVisaHistory(Integer id, VisaHistoryDto updated){
        VisaHistory existing = visaHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visa History Not Found"+id));

        Visa visa = new Visa();
        visa.setVisaId(updated.getVisaId());
        existing.setVisa(visa);

        existing.setHistoryId(updated.getHistoryId());
        existing.setTouristId(updated.getTouristId());
        existing.setAction(updated.getAction());
        existing.setRemarks(updated.getRemarks());
        existing.setActionDate(updated.getActionDate());

        return toDto(visaHistoryRepository.save(existing));
    }

    // partially update
    public VisaHistoryDto partialUpdateVisaHistory(Integer id, Map<String, Object> fields){
        VisaHistory existing = visaHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visa History Not Found"+id));

        if(fields.containsKey("visaId")){
            Visa visa = new Visa();
            visa.setVisaId(Integer.valueOf(fields.get("visaId").toString()));
            existing.setVisa(visa);
        }
        if(fields.containsKey("touristId")){
            existing.setTouristId(Long.valueOf(fields.get("touristId").toString()));
        }
        if(fields.containsKey("action")){
            existing.setAction((String) fields.get("action"));
        }
        if(fields.containsKey("remarks")){
            existing.setRemarks((String) fields.get("remarks"));
        }
        if(fields.containsKey("actionDate")){
            existing.setActionDate((LocalDateTime) fields.get("actionDate"));
        }

        return toDto(visaHistoryRepository.save(existing));
    }

    // delete
    public void deleteVisaHistory(Integer id){
        visaHistoryRepository.deleteById(id);
    }

    // pagination and sorting
    public Page<VisaHistoryDto> getPageableAllVisaHistories(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return visaHistoryRepository.findAll(pageable).map(this::toDto);
    }

    // filtering
    public Page<VisaHistoryDto> searchByHistoryId(Integer historyId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return visaHistoryRepository.findByHistoryId(historyId, pageable).map(this::toDto);
    }

    public Page<VisaHistoryDto> searchByVisaId(Integer visaId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return visaHistoryRepository.findByVisa_visaId(visaId, pageable).map(this::toDto);
    }

    public Page<VisaHistoryDto> searchByTouristId(Long touristId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return visaHistoryRepository.findByTouristId(touristId, pageable).map(this::toDto);
    }
}