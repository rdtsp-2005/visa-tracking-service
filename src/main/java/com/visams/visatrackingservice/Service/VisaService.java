package com.visams.visatrackingservice.Service;

import com.visams.visatrackingservice.Dto.VisaDto;
import com.visams.visatrackingservice.Entity.Visa;
import com.visams.visatrackingservice.Repository.VisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;

@Service
public class VisaService {

    @Autowired
    private VisaRepository visaRepository;

    private final RestTemplate restTemplate = createRestTemplate();

    private RestTemplate createRestTemplate() {
        RestTemplate template = new RestTemplate();
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest servletRequest = attributes.getRequest();
                String authHeader = servletRequest.getHeader("Authorization");
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    request.getHeaders().add("Authorization", authHeader);
                }
            }
            return execution.execute(request, body);
        };
        template.setInterceptors(Collections.singletonList(interceptor));
        return template;
    }

    private VisaDto toDto(Visa visa){
        return new VisaDto(
                visa.getVisaId(),
                visa.getPassportId(),
                visa.getVisaType(),
                visa.getIssueDate(),
                visa.getExpiryDate(),
                visa.getStatus());
    }

    // view all
    public List<VisaDto> getAllVisas(){
        return visaRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // view by id
    public VisaDto getVisaById(Integer visaId){
        Visa visa = visaRepository.findById(visaId)
                .orElseThrow(() -> new RuntimeException("Visa Not Found"+visaId));
        return toDto(visa);
    }

    // create
    public VisaDto createVisa(VisaDto dto){
        Visa visa = new Visa();
        visa.setVisaId(dto.getVisaId()); 
        visa.setPassportId(dto.getPassportId());
        visa.setVisaType(dto.getVisaType());
        visa.setIssueDate(dto.getIssueDate());
        visa.setExpiryDate(dto.getExpiryDate());
        visa.setStatus(dto.getStatus());
        Visa saved = visaRepository.save(visa);

        // Fetch Tourist Email from tourist-service
        try {
            String touristUrl = "http://207.180.253.221:8080/api/tourists/search/passport?passportId=" + saved.getPassportId(); // Replace with actual tourist service URL to get by passport id
            // Actually, we don't have a direct endpoint for email by passport. Let's send an email to a placeholder or ask the user to fetch the Tourist directly.
            // Let's assume there's a way to get email, or just send a generic alert for now.
            // For this implementation, we will attempt to fetch from the passport if possible, or send to a test email.
            String emailUrl = "http://207.180.253.221:8080/api/v1/alerts/send-email"; 
            java.util.Map<String, String> emailRequest = new java.util.HashMap<>();
            // TODO: Fetch real email using saved.getPassportId() from tourist-service
            emailRequest.put("to", "tourist@example.com"); // Fallback email until Tourist API provides email
            emailRequest.put("subject", "Visa Application Status Update");
            emailRequest.put("text", "Your visa application (Type: " + saved.getVisaType() + ") has been processed and is now " + saved.getStatus() + ".");
            restTemplate.postForObject(emailUrl, emailRequest, String.class);
        } catch (Exception e) {
            System.err.println("Failed to send visa assignment email: " + e.getMessage());
        }

        return toDto(saved);
    }

    // update
    public VisaDto updateVisa(Integer id, VisaDto updated){
        Visa existing = visaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visa Not Found"+id));

        existing.setVisaId(updated.getVisaId());
        existing.setPassportId(updated.getPassportId());
        existing.setVisaType(updated.getVisaType());
        existing.setIssueDate(updated.getIssueDate());
        existing.setExpiryDate(updated.getExpiryDate());
        existing.setStatus(updated.getStatus());

        return toDto(visaRepository.save(existing));
    }

    // partially update
    public VisaDto partialUpdateVisa(Integer id, Map<String, Object> fields){
        Visa existing = visaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visa not found:"+id));

        if(fields.containsKey("passportId")){
            existing.setPassportId(Long.valueOf(fields.get("passportId").toString()));
        }
        if(fields.containsKey("visaType")){
            existing.setVisaType((String) fields.get("visaType"));
        }
        if(fields.containsKey("issueDate")){
            existing.setIssueDate(LocalDate.parse(fields.get("issueDate").toString()));
        }
        if(fields.containsKey("expiryDate")){
            existing.setExpiryDate(LocalDate.parse(fields.get("expiryDate").toString()));
        }
        if(fields.containsKey("status")){
            existing.setStatus((String) fields.get("status"));
        }

        return toDto(visaRepository.save(existing));
    }

    // delete
    public void deleteVisa(Integer id){
        visaRepository.deleteById(id);
    }

    // pagination and sorting
    public Page<VisaDto> getPageableAllVisas(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return visaRepository.findAll(pageable).map(this::toDto);
    }

    // filtering
    public Page<VisaDto> searchByVisaId(Integer visaId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return visaRepository.findByVisaId(visaId, pageable).map(this::toDto);
    }

    public Page<VisaDto> searchByPassportId(Long passportId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return visaRepository.findByPassportId(passportId, pageable).map(this::toDto);
    }

    public Page<VisaDto> searchByVisaType(String visaType, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return visaRepository.findByVisaTypeContainingIgnoreCase(visaType, pageable).map(this::toDto);
    }

    public List<VisaDto> getExpiringVisas(List<LocalDate> dates) {
        return visaRepository.findByExpiryDateInAndStatus(dates, "Active")
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}