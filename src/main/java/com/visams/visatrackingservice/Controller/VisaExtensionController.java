package com.visams.visatrackingservice.Controller;

import com.visams.visatrackingservice.Dto.VisaExtensionDto;
import com.visams.visatrackingservice.Service.VisaExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/visaextensions")
public class VisaExtensionController {

    @Autowired
    private VisaExtensionService visaExtensionService;

    //view all
    @GetMapping
    public ResponseEntity<List<VisaExtensionDto>> getAllVisaExtensions() {
        return ResponseEntity.ok(visaExtensionService.getAllVisaExtensions());
    }

    //view by id
    @GetMapping("/view/{id}")
    public ResponseEntity<VisaExtensionDto> getVisaExtensionById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(visaExtensionService.getVisaExtensionById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // create new record
    @PostMapping
    public ResponseEntity<VisaExtensionDto> createVisaExtension(@RequestBody VisaExtensionDto visaExtensionDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(visaExtensionService.createVisaExtension(visaExtensionDto));
    }

    // update
    @PutMapping("/update/{id}")
    public ResponseEntity<VisaExtensionDto> updateVisaExtension(@PathVariable Integer id, @RequestBody VisaExtensionDto visaExtensionDto){
        try{
            return ResponseEntity.ok(visaExtensionService.updateVisaExtension(id, visaExtensionDto));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // partially update
    @PatchMapping("/partialupdate/{id}")
    public ResponseEntity<VisaExtensionDto> partialUpdateVisaExtension(@PathVariable Integer id, @RequestBody Map<String, Object> fields){
        try{
            return ResponseEntity.ok(visaExtensionService.partialUpdateVisaExtension(id, fields));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVisaExtension(@PathVariable Integer id){
        visaExtensionService.deleteVisaExtension(id);
        return ResponseEntity.noContent().build();
    }

    //pageable and sorting
    @GetMapping("/all")
    public ResponseEntity<Page<VisaExtensionDto>> getPageableAllVisaExtension(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "extensionId") String sortBy){
        return ResponseEntity.ok(visaExtensionService.getPageableAllVisaExtensions(page,size,sortBy));
    }

    //filtering
    @GetMapping("/search/extension")
    public ResponseEntity<Page<VisaExtensionDto>> searchByVisaExtensionId(
            @RequestParam Integer extensionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(visaExtensionService.searchByExtensionId(extensionId, page, size));
    }

    @GetMapping("/search/visa")
    public ResponseEntity<Page<VisaExtensionDto>> searchByVisaId(
            @RequestParam Integer visaId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(visaExtensionService.searchByVisaId(visaId, page, size));
    }

}
