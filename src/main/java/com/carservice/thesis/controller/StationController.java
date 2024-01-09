package com.carservice.thesis.controller;

import com.carservice.thesis.dto.ManagedStationDTO;
import com.carservice.thesis.dto.StationDTO;
import com.carservice.thesis.entity.Station;
import com.carservice.thesis.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stations")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;
    @PostMapping
    public ResponseEntity<StationDTO> createStation(@RequestBody StationDTO stationDTO) {
        return ResponseEntity.ok(stationService.createStation(stationDTO));
    }

    @PutMapping("/{stationId}")
    public ResponseEntity<StationDTO> updateStation(@PathVariable Integer stationId, @RequestBody StationDTO stationDTO) {
        return ResponseEntity.ok(stationService.updateStation(stationId, stationDTO));
    }

    @DeleteMapping("/{stationId}")
    public ResponseEntity<?> deleteStation(@PathVariable Integer stationId) {
        stationService.deleteStation(stationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{stationId}")
    public ResponseEntity<StationDTO> getStation(@PathVariable Integer stationId) {
        return ResponseEntity.ok(stationService.getStation(stationId));
    }

    @GetMapping
    public ResponseEntity<List<StationDTO>> getAllStations() {
        return ResponseEntity.ok(stationService.getAllStations());
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getManagedStationWithEmployees(Principal principal) {
        Optional<ManagedStationDTO> managedStationDTO = stationService.getStationManagedByUserWithEmployees(principal);

        if (managedStationDTO.isPresent()) {
            return ResponseEntity.ok(managedStationDTO.get());
        } else {
            // Returning an empty array when no station is found
            return ResponseEntity.ok(new ArrayList<>());
        }
    }
}
