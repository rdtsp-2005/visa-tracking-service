package com.visams.visatrackingservice.Controller;

import com.visams.visatrackingservice.Dto.LocationHistoryDto;
import com.visams.visatrackingservice.Service.LocationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/locationhistories")
public class LocationHistoryController {

    @Autowired
    private LocationHistoryService locationHistoryService;

    // view all
    @GetMapping
    public ResponseEntity<List<LocationHistoryDto>> getAllLocationHistories(){
        return ResponseEntity.ok(locationHistoryService.getAllLocationHistories());
    }

    // view by id
    @GetMapping("/view/{id}")
    public ResponseEntity<LocationHistoryDto> getLocationHistoryById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(locationHistoryService.getLocationHistoryById(id));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // create
    @PostMapping
    public ResponseEntity<LocationHistoryDto> createLocationHistory(@RequestBody LocationHistoryDto locationHistoryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(locationHistoryService.createLocationHistory(locationHistoryDto));
    }

    // update
    @PutMapping("/update/{id}")
    public ResponseEntity<LocationHistoryDto> updateLocationHistory(@PathVariable Integer id, @RequestBody LocationHistoryDto locationHistoryDto){
        try{
            return ResponseEntity.ok(locationHistoryService.updateLocationHistory(id, locationHistoryDto));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // partially update
    @PatchMapping("/partialupdate/{id}")
    public ResponseEntity<LocationHistoryDto> partialUpdateLocationHistory(@PathVariable Integer id, @RequestBody Map<String, Object> fields){
        try{
            return ResponseEntity.ok(locationHistoryService.partialUpdateLocationHistory(id, fields));
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLocationHistory(@PathVariable Integer id){
        locationHistoryService.deleteLocationHistory(id);
        return ResponseEntity.noContent().build();
    }

    // pagination and sorting
    @GetMapping("/all")
    public ResponseEntity<Page<LocationHistoryDto>> getPageableAllLocationHistories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "locationId") String sortBy){
        return ResponseEntity.ok(locationHistoryService.getPageableAllLocationHistories(page, size, sortBy));
    }

    // filtering
    @GetMapping("/search/location")
    public ResponseEntity<Page<LocationHistoryDto>> searchByLocationId(
            @RequestParam Integer locationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(locationHistoryService.searchByLocationId(locationId, page, size));
    }

    @GetMapping("/search/tourist")
    public ResponseEntity<Page<LocationHistoryDto>> searchByTouristId(
            @RequestParam Long touristId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(locationHistoryService.searchByTouristId(touristId, page, size));
    }

    @GetMapping("/search/name")
    public ResponseEntity<Page<LocationHistoryDto>> searchByLocationName(
            @RequestParam String locationName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(locationHistoryService.searchByLocationName(locationName, page, size));
    }

    @GetMapping("/search/type")
    public ResponseEntity<Page<LocationHistoryDto>> searchByLocationType(
            @RequestParam String locationType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(locationHistoryService.searchByLocationType(locationType, page, size));
    }
}
