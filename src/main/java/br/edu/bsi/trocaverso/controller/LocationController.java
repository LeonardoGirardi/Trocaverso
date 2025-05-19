package br.edu.bsi.trocaverso.controller;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpStatus;

import br.edu.bsi.trocaverso.dto.*;
import br.edu.bsi.trocaverso.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
}
