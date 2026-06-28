package com.visams.visatrackingservice.Controller;

import com.visams.visatrackingservice.Dto.VisaHistoryDto;
import com.visams.visatrackingservice.Service.VisaHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/visahistories")
public class VisaHistoryController {

    @Autowired
    private VisaHistoryService visaHistoryService;

    // view all
    @GetMapping
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'TOURIST_POLICE', 'HOTEL_STAFF', 'TRAVEL_AGENCY_STAFF')")
    public ResponseEntity<List<VisaHistoryDto>> getAllVisaHistories(){
        return ResponseEntity.ok(visaHistoryService.getAllVisaHistories());
    }

    // view by id
    @GetMapping("/view/{id}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'TOURIST_POLICE', 'HOTEL_STAFF', 'TRAVEL_AGENCY_STAFF')")
    public ResponseEntity<VisaHistoryDto> getVisaHistoryById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(visaHistoryService.getVisaHistoryById(id));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // create
    @PostMapping
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public ResponseEntity<VisaHistoryDto> createVisaHistory(@RequestBody VisaHistoryDto visaHistoryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(visaHistoryService.createVisaHistory(visaHistoryDto));
    }

    // update
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public ResponseEntity<VisaHistoryDto> updateVisaHistory(@PathVariable Integer id, @RequestBody VisaHistoryDto visaHistoryDto){
        try{
            return ResponseEntity.ok(visaHistoryService.updateVisaHistory(id, visaHistoryDto));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // partially update
    @PatchMapping("/partialupdate/{id}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public ResponseEntity<VisaHistoryDto> partialUpdateVisaHistory(@PathVariable Integer id, @RequestBody Map<String, Object> fields){
        try{
            return ResponseEntity.ok(visaHistoryService.partialUpdateVisaHistory(id, fields));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // delete
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN')")
    public ResponseEntity<Void> deleteVisaHistory(@PathVariable Integer id){
        visaHistoryService.deleteVisaHistory(id);
        return ResponseEntity.noContent().build();
    }

    // pagination and sorting
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'TOURIST_POLICE', 'HOTEL_STAFF', 'TRAVEL_AGENCY_STAFF')")
    public ResponseEntity<Page<VisaHistoryDto>> getPageableAllVisaHistories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "historyId") String sortBy){
        return ResponseEntity.ok(visaHistoryService.getPageableAllVisaHistories(page, size, sortBy));
    }

    // filtering
    @GetMapping("/search/history")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'TOURIST_POLICE', 'HOTEL_STAFF', 'TRAVEL_AGENCY_STAFF')")
    public ResponseEntity<Page<VisaHistoryDto>> searchByHistoryId(
            @RequestParam Integer historyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(visaHistoryService.searchByHistoryId(historyId, page, size));
    }

    @GetMapping("/search/visa")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'TOURIST_POLICE', 'HOTEL_STAFF', 'TRAVEL_AGENCY_STAFF')")
    public ResponseEntity<Page<VisaHistoryDto>> searchByVisaId(
            @RequestParam Integer visaId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(visaHistoryService.searchByVisaId(visaId, page, size));
    }

    @GetMapping("/search/tourist")
    @PreAuthorize("hasAnyRole('IMMIGRATION_OFFICER', 'ADMIN', 'TOURIST_POLICE', 'HOTEL_STAFF', 'TRAVEL_AGENCY_STAFF')")
    public ResponseEntity<Page<VisaHistoryDto>> searchByTouristId(
            @RequestParam Long touristId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(visaHistoryService.searchByTouristId(touristId, page, size));
    }
}