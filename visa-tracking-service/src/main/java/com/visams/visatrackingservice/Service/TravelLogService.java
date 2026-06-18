package com.visams.visatrackingservice.Service;

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

@Service
public class TravelLogService {

    @Autowired
    private TravelLogRepository travelLogRepository;

    public List<TravelLog> getAllTravelLogs(){
        return travelLogRepository.findAll();
    }

    public TravelLog getTravelLogById(Integer id){
        return travelLogRepository.findById(id).orElseThrow(() -> new RuntimeException("Travel Log Not Found"+id));
    }

    public TravelLog createTravelLog(TravelLog travelLog){
        return travelLogRepository.save(travelLog);
    }

    public TravelLog updateTravelLog(Integer id , TravelLog updated){
        TravelLog existing = travelLogRepository.findById(id).orElseThrow(() -> new RuntimeException("Travel Log Not Found"+id));

        existing.setLogId(updated.getLogId());
        existing.setTourist(updated.getTourist());
        existing.setLocation(updated.getLocation());
        existing.setCheckInDate(updated.getCheckInDate());
        existing.setCheckOutDate(updated.getCheckOutDate());

        return travelLogRepository.save(existing);
    }

    public TravelLog partialUpdateTravelLog(Integer id, Map<String, Object> fields){
        TravelLog existing = travelLogRepository.findById(id).orElseThrow(() -> new RuntimeException("Travel Log Not Found"+id));

        if(fields.containsKey("location")){
            existing.setLocation((String) fields.get("location"));
        }

        if(fields.containsKey("checkInDate")){
            existing.setCheckInDate((LocalDate)  fields.get("checkInDate"));
        }

        if(fields.containsKey("checkOutDate")){
            existing.setCheckOutDate((LocalDate)  fields.get("checkOutDate"));
        }

        return travelLogRepository.save(existing);
    }

    public void deleteTravelLog(Integer id){
        travelLogRepository.deleteById(id);
    }

    //pagination and sorting
    public Page<TravelLog> getPageableAllTravelLogs(int page, int size,String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return travelLogRepository.findAll(pageable);
    }

    //filtering
    public Page<TravelLog> searchByLogId(Integer logId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return travelLogRepository.findByLogId(logId, pageable);
    }

    public Page<TravelLog> searchByTouristId(Integer touristId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return travelLogRepository.findByTourist_touristId(touristId, pageable);
    }

    public Page<TravelLog> searchByLocation(String location, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return travelLogRepository.findByLocationContainingIgnoreCase(location, pageable);
    }
}
