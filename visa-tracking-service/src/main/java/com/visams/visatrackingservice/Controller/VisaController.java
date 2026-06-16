package com.visams.visatrackingservice.Controller;

import com.visams.visatrackingservice.Entity.Visa;
import com.visams.visatrackingservice.Service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Visa> deleteVisa(@PathVariable Integer id){
        visaService.deleteVisa(id);
        return ResponseEntity.noContent().build();
    }
}
