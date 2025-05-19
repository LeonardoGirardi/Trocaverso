package br.edu.bsi.trocaverso.dto;

import br.edu.bsi.trocaverso.model.Item;
import br.edu.bsi.trocaverso.model.ServiceOffer;
import br.edu.bsi.trocaverso.model.UserProfile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserProfileResponseDTO {

    private UUID userId;
    private String profileBio;
    private String profilePicturePath;
    private List<String> itemsForTrade;
    private List<String> servicesForTrade;

    public UserProfileResponseDTO(UserProfile profile) {
        this.userId = profile.getUserAccount().getId();
        this.profileBio = profile.getProfileBio();
        this.profilePicturePath = profile.getProfilePicturePath();
        this.itemsForTrade = profile.getItemsForTrade().stream()
                .map(Item::getItemName) // ou outro campo que represente o nome do item
                .collect(Collectors.toList());
        this.servicesForTrade = profile.getServicesForTrade().stream()
                .map(ServiceOffer::getServiceName) // idem para serviços
                .collect(Collectors.toList());
    }

    // Getters
    public UUID getUserId() {
        return userId;
    }

    public String getProfileBio() {
        return profileBio;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public List<String> getItemsForTrade() {
        return itemsForTrade;
    }

    public List<String> getServicesForTrade() {
        return servicesForTrade;
    }
}
