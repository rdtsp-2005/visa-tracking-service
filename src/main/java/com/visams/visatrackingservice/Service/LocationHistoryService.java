package com.visams.visatrackingservice.Service;

import com.visams.visatrackingservice.Dto.LocationHistoryDto;
import com.visams.visatrackingservice.Entity.LocationHistory;
import com.visams.visatrackingservice.Repository.LocationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LocationHistoryService {

    @Autowired
    private LocationHistoryRepository locationHistoryRepository;

    private LocationHistoryDto toDto(LocationHistory locationHistory){
        return new LocationHistoryDto(
                locationHistory.getLocationId(),
                locationHistory.getTouristId(),
                locationHistory.getLocationName(),
                locationHistory.getLocationType(),
                locationHistory.getRecordedAt());
    }

    // view all
    public List<LocationHistoryDto> getAllLocationHistories(){
        return locationHistoryRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // view by id
    public LocationHistoryDto getLocationHistoryById(Integer id){
        LocationHistory locationHistory = locationHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location History Not Found"+id));
        return toDto(locationHistory);
    }

    // create
    public LocationHistoryDto createLocationHistory(LocationHistoryDto dto){
        LocationHistory locationHistory = new LocationHistory();
        locationHistory.setTouristId(dto.getTouristId());
        locationHistory.setLocationName(dto.getLocationName());
        locationHistory.setLocationType(dto.getLocationType());
        locationHistory.setRecordedAt(dto.getRecordedAt());
        return toDto(locationHistoryRepository.save(locationHistory));
    }

    // update
    public LocationHistoryDto updateLocationHistory(Integer id, LocationHistoryDto updated){
        LocationHistory existing = locationHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location History Not Found"+id));

        existing.setLocationId(updated.getLocationId());
        existing.setTouristId(updated.getTouristId());
        existing.setLocationName(updated.getLocationName());
        existing.setLocationType(updated.getLocationType());
        existing.setRecordedAt(updated.getRecordedAt());

        return toDto(locationHistoryRepository.save(existing));
    }

    // partially update
    public LocationHistoryDto partialUpdateLocationHistory(Integer id, Map<String, Object> fields){
        LocationHistory existing = locationHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location History Not Found"+id));

        if(fields.containsKey("touristId")){
            existing.setTouristId(Long.valueOf(fields.get("touristId").toString()));
        }
        if(fields.containsKey("locationName")){
            existing.setLocationName((String) fields.get("locationName"));
        }
        if(fields.containsKey("locationType")){
            existing.setLocationType((String) fields.get("locationType"));
        }
        if(fields.containsKey("recordedAt")){
            existing.setRecordedAt((LocalDateTime) fields.get("recordedAt"));
        }

        return toDto(locationHistoryRepository.save(existing));
    }

    // delete
    public void deleteLocationHistory(Integer id){
        locationHistoryRepository.deleteById(id);
    }

    // pagination and sorting
    public Page<LocationHistoryDto> getPageableAllLocationHistories(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return locationHistoryRepository.findAll(pageable).map(this::toDto);
    }

    // filtering
    public Page<LocationHistoryDto> searchByLocationId(Integer locationId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return locationHistoryRepository.findByLocationId(locationId, pageable).map(this::toDto);
    }

    public Page<LocationHistoryDto> searchByTouristId(Long touristId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return locationHistoryRepository.findByTouristId(touristId, pageable).map(this::toDto);
    }

    public Page<LocationHistoryDto> searchByLocationName(String locationName, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return locationHistoryRepository.findByLocationNameContainingIgnoreCase(locationName, pageable).map(this::toDto);
    }

    public Page<LocationHistoryDto> searchByLocationType(String locationType, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return locationHistoryRepository.findByLocationType(locationType, pageable).map(this::toDto);
    }
}