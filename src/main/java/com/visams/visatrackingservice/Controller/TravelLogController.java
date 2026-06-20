package com.visams.visatrackingservice.Controller;

import com.visams.visatrackingservice.Dto.TravelLogDto;
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
    public ResponseEntity<List<TravelLogDto>> getAllTravelLogs(){
        return ResponseEntity.ok(travelLogService.getAllTravelLogs());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<TravelLogDto> getTravelLogById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(travelLogService.getTravelLogById(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TravelLogDto> createTravelLog(@RequestBody TravelLogDto travelLogDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(travelLogService.createTravelLog(travelLogDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TravelLogDto> updateTravelLog(@PathVariable Integer id, @RequestBody TravelLogDto travelLogDto){
        try{
            return ResponseEntity.ok(travelLogService.updateTravelLog(id, travelLogDto));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/partialupdate/{id}")
    public ResponseEntity<TravelLogDto> partialUpdateTravelLog(@PathVariable Integer id, @RequestBody Map<String, Object> fields){
        try{
            return ResponseEntity.ok(travelLogService.partialUpdateTravelLog(id, fields));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTravelLog(@PathVariable Integer id){
        travelLogService.deleteTravelLog(id);
        return ResponseEntity.noContent().build();
    }

    //pagination and sorting
    @GetMapping("/all")
    public ResponseEntity<Page<TravelLogDto>> getPageableAllTravelLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "logId") String sortBy){
        return ResponseEntity.ok(travelLogService.getPageableAllTravelLogs(page, size, sortBy));
    }

    //filtering
    @GetMapping("/search/log")
    public ResponseEntity<Page<TravelLogDto>> searchByLogId(
            @RequestParam Integer logId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(travelLogService.searchByLogId(logId, page, size));
    }

    @GetMapping("/search/tourist")
    public ResponseEntity<Page<TravelLogDto>> searchByTouristId(
            @RequestParam Long touristId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(travelLogService.searchByTouristId(touristId, page, size));
    }

    @GetMapping("/search/location")
    public ResponseEntity<Page<TravelLogDto>> searchByLocation(
            @RequestParam String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(travelLogService.searchByLocation(location, page, size));
    }

}
