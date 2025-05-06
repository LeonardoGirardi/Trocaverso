package br.edu.bsi.trocaverso.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_service_offer")
public class ServiceOffer extends GenericModel{

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "service_description", nullable = false)
    private String description;

    @Column(name = "service_picture_path", nullable = true)
    private String servicePicturePath;

    @ManyToOne
    @JoinColumn(name = "service_provider", nullable = false)
    private UserProfile provider;

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

    public UserProfile getProvider() {
        return provider;
    }

    public void setProvider(UserProfile provider) {
        this.provider = provider;
    }
}
