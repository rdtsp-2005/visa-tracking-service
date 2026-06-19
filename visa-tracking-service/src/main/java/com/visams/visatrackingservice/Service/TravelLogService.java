package com.visams.visatrackingservice.Service;

import com.visams.visatrackingservice.Dto.TravelLogDto;
import com.visams.visatrackingservice.Entity.Tourist;
import com.visams.visatrackingservice.Entity.TravelLog;
import com.visams.visatrackingservice.Repository.TravelLogRepository;
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
public class TravelLogService {

    @Autowired
    private TravelLogRepository travelLogRepository;

    private TravelLogDto toDto(TravelLog travelLog){
        return new TravelLogDto(
                travelLog.getLogId(),
                travelLog.getTourist().getTouristId(),
                travelLog.getLocation(),
                travelLog.getCheckInDate(),
                travelLog.getCheckOutDate());
    }

    //view all
    public List<TravelLogDto> getAllTravelLogs(){
        return travelLogRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    //view by id
    public TravelLogDto getTravelLogById(Integer id){
        TravelLog travelLog = travelLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel Log Not Found"+id));
        return toDto(travelLog);
    }

    //create
    public TravelLogDto createTravelLog(TravelLogDto dto){
        TravelLog travelLog = new TravelLog();

        Tourist tourist = new Tourist();
        tourist.setTouristId(dto.getTouristId());
        travelLog.setTourist(tourist);

        travelLog.setLocation(dto.getLocation());
        travelLog.setCheckInDate(dto.getCheckInDate());
        travelLog.setCheckOutDate(dto.getCheckOutDate());
        return toDto(travelLogRepository.save(travelLog));
    }

    //update
    public TravelLogDto updateTravelLog(Integer id, TravelLogDto updated){
        TravelLog existing = travelLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel Log Not Found"+id));

        Tourist tourist = new Tourist();
        tourist.setTouristId(updated.getTouristId());
        existing.setTourist(tourist);

        existing.setLogId(updated.getLogId());
        existing.setLocation(updated.getLocation());
        existing.setCheckInDate(updated.getCheckInDate());
        existing.setCheckOutDate(updated.getCheckOutDate());

        return toDto(travelLogRepository.save(existing));
    }

    // partially update
    public TravelLogDto partialUpdateTravelLog(Integer id, Map<String, Object> fields){
        TravelLog existing = travelLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel Log Not Found"+id));

        if(fields.containsKey("touristId")){
            Tourist tourist = new Tourist();
            tourist.setTouristId(Long.valueOf(fields.get("touristId").toString()));
            existing.setTourist(tourist);
        }
        if(fields.containsKey("location")){
            existing.setLocation((String) fields.get("location"));
        }
        if(fields.containsKey("checkInDate")){
            existing.setCheckInDate((LocalDate) fields.get("checkInDate"));
        }
        if(fields.containsKey("checkOutDate")){
            existing.setCheckOutDate((LocalDate) fields.get("checkOutDate"));
        }

        return toDto(travelLogRepository.save(existing));
    }

    //delete
    public void deleteTravelLog(Integer id){
        travelLogRepository.deleteById(id);
    }

    //pagination and sorting
    public Page<TravelLogDto> getPageableAllTravelLogs(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return travelLogRepository.findAll(pageable).map(this::toDto);
    }

    //filtering
    public Page<TravelLogDto> searchByLogId(Integer logId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return travelLogRepository.findByLogId(logId, pageable).map(this::toDto);
    }

    public Page<TravelLogDto> searchByTouristId(Long touristId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return travelLogRepository.findByTourist_touristId(touristId, pageable).map(this::toDto);
    }

    public Page<TravelLogDto> searchByLocation(String location, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return travelLogRepository.findByLocationContainingIgnoreCase(location, pageable).map(this::toDto);
    }
}