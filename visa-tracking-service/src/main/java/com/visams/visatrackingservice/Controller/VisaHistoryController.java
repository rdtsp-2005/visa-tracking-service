package com.visams.visatrackingservice.Controller;

import com.visams.visatrackingservice.Dto.VisaHistoryDto;
import com.visams.visatrackingservice.Service.VisaHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/visahistories")
public class VisaHistoryController {

    @Autowired
    private VisaHistoryService visaHistoryService;

    // view all
    @GetMapping
    public ResponseEntity<List<VisaHistoryDto>> getAllVisaHistories(){
        return ResponseEntity.ok(visaHistoryService.getAllVisaHistories());
    }

    // view by id
    @GetMapping("/view/{id}")
    public ResponseEntity<VisaHistoryDto> getVisaHistoryById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(visaHistoryService.getVisaHistoryById(id));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // create
    @PostMapping
    public ResponseEntity<VisaHistoryDto> createVisaHistory(@RequestBody VisaHistoryDto visaHistoryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(visaHistoryService.createVisaHistory(visaHistoryDto));
    }

    // update
    @PutMapping("/update/{id}")
    public ResponseEntity<VisaHistoryDto> updateVisaHistory(@PathVariable Integer id, @RequestBody VisaHistoryDto visaHistoryDto){
        try{
            return ResponseEntity.ok(visaHistoryService.updateVisaHistory(id, visaHistoryDto));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // partially update
    @PatchMapping("/partialupdate/{id}")
    public ResponseEntity<VisaHistoryDto> partialUpdateVisaHistory(@PathVariable Integer id, @RequestBody Map<String, Object> fields){
        try{
            return ResponseEntity.ok(visaHistoryService.partialUpdateVisaHistory(id, fields));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVisaHistory(@PathVariable Integer id){
        visaHistoryService.deleteVisaHistory(id);
        return ResponseEntity.noContent().build();
    }

    // pagination and sorting
    @GetMapping("/all")
    public ResponseEntity<Page<VisaHistoryDto>> getPageableAllVisaHistories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "historyId") String sortBy){
        return ResponseEntity.ok(visaHistoryService.getPageableAllVisaHistories(page, size, sortBy));
    }

    // filtering
    @GetMapping("/search/history")
    public ResponseEntity<Page<VisaHistoryDto>> searchByHistoryId(
            @RequestParam Integer historyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(visaHistoryService.searchByHistoryId(historyId, page, size));
    }

    @GetMapping("/search/visa")
    public ResponseEntity<Page<VisaHistoryDto>> searchByVisaId(
            @RequestParam Integer visaId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(visaHistoryService.searchByVisaId(visaId, page, size));
    }

    @GetMapping("/search/tourist")
    public ResponseEntity<Page<VisaHistoryDto>> searchByTouristId(
            @RequestParam Long touristId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(visaHistoryService.searchByTouristId(touristId, page, size));
    }
}