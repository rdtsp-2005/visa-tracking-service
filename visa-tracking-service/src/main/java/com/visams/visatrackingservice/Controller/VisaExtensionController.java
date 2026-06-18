package com.visams.visatrackingservice.Controller;

import com.visams.visatrackingservice.Entity.VisaExtension;
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
    private VisaExtensionService  visaExtensionService;

    @GetMapping
    public ResponseEntity<List<VisaExtension>> getAllVisaExtensions() {
        return ResponseEntity.ok(visaExtensionService.getAllVisaExtensions());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<VisaExtension> getVisaExtensionById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(visaExtensionService.getVisaExtensionById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<VisaExtension> createVisaExtension(@RequestBody VisaExtension visaExtension){
        return ResponseEntity.status(HttpStatus.CREATED).body(visaExtensionService.createVisaExtension(visaExtension));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VisaExtension> updateVisaExtension(@PathVariable Integer id, @RequestBody VisaExtension visaExtension){
        try{
            return ResponseEntity.ok(visaExtensionService.updateVisaExtension(id, visaExtension));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/partialupdate/{id}")
    public ResponseEntity<VisaExtension> partialUpdateVisaExtension(@PathVariable Integer id, @RequestBody Map<String, Object> fields){
        try{
            return ResponseEntity.ok(visaExtensionService.partialUpdateVisaExtension(id, fields));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<VisaExtension> deleteVisaExtension(@PathVariable Integer id){
        visaExtensionService.deleteVisaExtension(id);
        return ResponseEntity.noContent().build();
    }

    //pageable and sorting
    @GetMapping
    public ResponseEntity<Page<VisaExtension>> getPageableAllVisaExtension(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "extensionid") String sortBy){
        return ResponseEntity.ok(visaExtensionService.getPageableAllVisaExtensions(page,size,sortBy));
    }

    @GetMapping("/search/extension")
    public ResponseEntity<Page<VisaExtension>> searchByVisaExtensionId(
            @RequestParam Integer extensionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(visaExtensionService.searchByExtensionId(extensionId, page, size));
    }

    @GetMapping("/search/visa")
    public ResponseEntity<Page<VisaExtension>> searchByVisaId(
            @RequestParam Integer visaId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(visaExtensionService.searchByVisaId(visaId, page, size));
    }

}
