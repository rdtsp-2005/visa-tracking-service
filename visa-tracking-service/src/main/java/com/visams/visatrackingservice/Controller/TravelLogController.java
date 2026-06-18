package com.visams.visatrackingservice.Controller;

import com.visams.visatrackingservice.Entity.TravelLog;
import com.visams.visatrackingservice.Service.TravelLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/travellogs")
public class TravelLogController {

    @Autowired
    private TravelLogService travelLogService;

    @GetMapping
    public ResponseEntity<List<TravelLog>> getAllTravelLogs(){
        return ResponseEntity.ok(travelLogService.getAllTravelLogs());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<TravelLog> getTravelLogById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(travelLogService.getTravelLogById(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TravelLog> createTravelLog(@RequestBody TravelLog travelLog){
        return ResponseEntity.status(HttpStatus.CREATED).body(travelLogService.createTravelLog(travelLog));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TravelLog>  updateTravelLog(@PathVariable Integer id, @RequestBody TravelLog travelLog){
        try{
            return ResponseEntity.ok(travelLogService.updateTravelLog(id, travelLog));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/partialupdate/{id}")
    public ResponseEntity<TravelLog> partialUpdateTravelLog(@PathVariable Integer id, @RequestBody Map<String, Object> fields){
        try{
            return ResponseEntity.ok(travelLogService.partialUpdateTravelLog(id, fields));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TravelLog> deleteTravelLog(@PathVariable Integer id){
        travelLogService.deleteTravelLog(id);
        return ResponseEntity.noContent().build();
    }

    //pagination and sorting
    @GetMapping
    public ResponseEntity<Page<TravelLog>> getPageableAllTravelLogs(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size,
            @RequestParam(defaultValue = "logid") String sortBy){
        return ResponseEntity.ok(travelLogService.getPageableAllTravelLogs(page, size, sortBy));
    }

    //filtering
    @GetMapping("/search/log")
    public ResponseEntity<Page<TravelLog>> searchByLogId(
            @RequestParam Integer logId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(travelLogService.searchByLogId(logId, page, size));
    }

    @GetMapping("/search/tourist")
    public ResponseEntity<Page<TravelLog>> searchByTouristId(
            @RequestParam Integer touristId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(travelLogService.searchByTouristId(touristId, page, size));
    }

    @GetMapping("/search/location")
    public ResponseEntity<Page<TravelLog>> searchByLocation(
            @RequestParam String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(travelLogService.searchByLocation(location, page, size));
    }

}
