package com.visams.visatrackingservice.Controller;

import com.visams.visatrackingservice.Entity.Visa;
import com.visams.visatrackingservice.Service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/visas")
public class VisaController {

    @Autowired
    private VisaService visaService;

    @GetMapping
    public ResponseEntity<List<Visa>> getAllVisas(){
        return ResponseEntity.ok(visaService.getAllVisas());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Visa> getVisaById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(visaService.getVisaById(id));
        }
        catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Visa> createVisa(@RequestBody Visa visa){
        return ResponseEntity.status(HttpStatus.CREATED).body(visaService.createVisa(visa));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Visa> updateVisa(@PathVariable Integer id, @RequestBody Visa visa){
        try {
            return ResponseEntity.ok(visaService.updateVisa(id, visa));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/partialupdate/{id}")
    public ResponseEntity<Visa> partialUpdateVisa(@PathVariable Integer id, @RequestBody Map<String, Object> fields){
        try{
            return ResponseEntity.ok(visaService.partialUpdateVisa(id, fields));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Visa> deleteVisa(@PathVariable Integer id){
        visaService.deleteVisa(id);
        return ResponseEntity.noContent().build();
    }

    //pagination and sorting
    @GetMapping
    public ResponseEntity<Page<Visa>> getPageableAllVisas(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @RequestParam(defaultValue = "visaid") String sortBy){
        return ResponseEntity.ok(visaService.getPageableAllVisas(page,size,sortBy));
    }

    //filtering
    @GetMapping("/search")
    public ResponseEntity<Page<Visa>> searchByVisaId(@RequestParam Integer visaId,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size, @PathVariable String id){
        return ResponseEntity.ok(visaService.searchByVisaId(visaId,page,size));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Visa>> searchByTouristId(
            @RequestParam Integer touristId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(visaService.searchByTouristId(touristId, page, size));
    }

    @GetMapping("/search/type")
    public ResponseEntity<Page<Visa>> searchByVisaType(
            @RequestParam String visaType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(visaService.searchByVisaType(visaType, page, size));
    }

}
