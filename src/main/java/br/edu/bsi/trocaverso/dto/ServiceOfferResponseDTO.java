package br.edu.bsi.trocaverso.dto;

import java.util.UUID;

public class ServiceOfferResponseDTO {
    private UUID id;
    private String serviceName;
    private String description;
    private String servicePicturePath;
    private UUID providerId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServicePicturePath() {
        return servicePicturePath;
    }

    public void setServicePicturePath(String servicePicturePath) {
        this.servicePicturePath = servicePicturePath;
    }

    public UUID getProviderId() {
        return providerId;
    }

    public void setProviderId(UUID providerId) {
        this.providerId = providerId;
    }
}
