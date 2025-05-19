package br.edu.bsi.trocaverso.service;

import br.edu.bsi.trocaverso.dto.*;
import br.edu.bsi.trocaverso.model.*;
import br.edu.bsi.trocaverso.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServiceOfferService {

    private final ServiceOfferRepository serviceOfferRepository;
    private final UserProfileRepository userProfileRepository;

    public ServiceOfferService(ServiceOfferRepository serviceOfferRepository, UserProfileRepository userProfileRepository) {
        this.serviceOfferRepository = serviceOfferRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public ServiceOfferResponseDTO create(ServiceOfferRequestDTO dto) {
        UserProfile provider = userProfileRepository.findById(dto.getProviderId()).orElse(null);
        if (provider == null) return null;

        ServiceOffer service = new ServiceOffer();
        service.setServiceName(dto.getServiceName());
        service.setDescription(dto.getDescription());
        service.setServicePicturePath(dto.getServicePicturePath());
        service.setProvider(provider);
        service = serviceOfferRepository.save(service);

        return toDTO(service);
    }

    public List<ServiceOfferResponseDTO> findAll() {
        return serviceOfferRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ServiceOfferResponseDTO findById(UUID id) {
        return serviceOfferRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public ServiceOfferResponseDTO update(UUID id, ServiceOfferRequestDTO dto) {
        ServiceOffer service = serviceOfferRepository.findById(id).orElse(null);
        if (service == null) return null;

        UserProfile provider = userProfileRepository.findById(dto.getProviderId()).orElse(null);
        if (provider == null) return null;

        service.setServiceName(dto.getServiceName());
        service.setDescription(dto.getDescription());
        service.setServicePicturePath(dto.getServicePicturePath());
        service.setProvider(provider);
        service = serviceOfferRepository.save(service);

        return toDTO(service);
    }

    public void delete(UUID id) {
        serviceOfferRepository.deleteById(id);
    }

    private ServiceOfferResponseDTO toDTO(ServiceOffer service) {
        ServiceOfferResponseDTO dto = new ServiceOfferResponseDTO();
        dto.setId(service.getId());
        dto.setServiceName(service.getServiceName());
        dto.setDescription(service.getDescription());
        dto.setServicePicturePath(service.getServicePicturePath());
        dto.setProviderId(service.getProvider().getId());
        return dto;
    }
}
