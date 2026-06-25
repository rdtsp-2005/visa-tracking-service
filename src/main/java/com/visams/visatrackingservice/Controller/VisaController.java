package com.visams.visatrackingservice.Controller;

import com.visams.visatrackingservice.Dto.VisaDto;
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

    //view all
    @GetMapping
    public ResponseEntity<List<VisaDto>> getAllVisas(){
        return ResponseEntity.ok(visaService.getAllVisas());
    }

    //view by id
    @GetMapping("/view/{id}")
    public ResponseEntity<VisaDto> getVisaById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(visaService.getVisaById(id));
        }
        catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // create new one
    @PostMapping
    public ResponseEntity<VisaDto> createVisa(@RequestBody VisaDto visa){
        return ResponseEntity.status(HttpStatus.CREATED).body(visaService.createVisa(visa));
    }

    // update
    @PutMapping("/update/{id}")
    public ResponseEntity<VisaDto> updateVisa(@PathVariable Integer id, @RequestBody VisaDto visa){
        try {
            return ResponseEntity.ok(visaService.updateVisa(id, visa));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // partially update
    @PatchMapping("/partialupdate/{id}")
    public ResponseEntity<VisaDto> partialUpdateVisa(@PathVariable Integer id, @RequestBody Map<String, Object> fields){
        try{
            return ResponseEntity.ok(visaService.partialUpdateVisa(id, fields));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVisa(@PathVariable Integer id){
        visaService.deleteVisa(id);
        return ResponseEntity.noContent().build();
    }

    // pagination and sorting
    @GetMapping("/all")
    public ResponseEntity<Page<VisaDto>> getPageableAllVisas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "visaId") String sortBy){
        return ResponseEntity.ok(visaService.getPageableAllVisas(page, size, sortBy));
    }

    // filtering
    @GetMapping("/search")
    public ResponseEntity<Page<VisaDto>> searchByVisaId(
            @RequestParam Integer visaId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(visaService.searchByVisaId(visaId, page, size));
    }

    @GetMapping("/search/passport")
    public ResponseEntity<Page<VisaDto>> searchByPassportId(
            @RequestParam Long passportId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(visaService.searchByPassportId(passportId, page, size));
    }

    @GetMapping("/search/type")
    public ResponseEntity<Page<VisaDto>> searchByVisaType(
            @RequestParam String visaType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(visaService.searchByVisaType(visaType, page, size));
    }

    @GetMapping("/expiring")
    public ResponseEntity<List<VisaDto>> getExpiringVisas(@RequestParam List<java.time.LocalDate> dates) {
        return ResponseEntity.ok(visaService.getExpiringVisas(dates));
    }
    //test

}
