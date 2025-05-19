package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.model.Location;
import br.edu.bsi.trocaverso.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location findById(UUID id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada: " + id));
    }
}
