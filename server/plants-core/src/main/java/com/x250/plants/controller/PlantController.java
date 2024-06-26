package com.x250.plants.controller;

import com.x250.plants.dto.PlantCreateDTO;
import com.x250.plants.dto.PlantResponseDTO;
import com.x250.plants.exception.EntityNotFoundException;
import com.x250.plants.model.Plant;
import com.x250.plants.service.PlantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plant")
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    /**
     * If data not valid 400 status code returned and message in the console
     */

    @GetMapping("/name")
    @ResponseStatus(HttpStatus.OK)
    public List<PlantResponseDTO> searchPlantsByName(
            @RequestParam String name
    ) {
        return plantService.searchPlantsByName(name);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlantResponseDTO getPlantById(
            @PathVariable String id
    ) throws EntityNotFoundException {
        return plantService.getPlantById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public PlantResponseDTO addPlant(@Valid @RequestBody PlantCreateDTO plantCreateDTO) {
        return plantService.addPlant(plantCreateDTO);
    }


}
