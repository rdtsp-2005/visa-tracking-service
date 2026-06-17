package com.visams.visatrackingservice.Service;

import com.visams.visatrackingservice.Entity.TravelLog;
import com.visams.visatrackingservice.Repository.TravelLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
