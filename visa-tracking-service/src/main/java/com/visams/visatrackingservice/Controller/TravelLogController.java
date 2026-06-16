package com.visams.visatrackingservice.Controller;

import com.visams.visatrackingservice.Entity.TravelLog;
import com.visams.visatrackingservice.Service.TravelLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TravelLog> deleteTravelLog(@PathVariable Integer id){
        travelLogService.deleteTravelLog(id);
        return ResponseEntity.noContent().build();
    }

}
