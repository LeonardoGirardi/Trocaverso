package br.edu.bsi.trocaverso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserProfileRequestDTO {

    @NotBlank(message = "A biografia do perfil é obrigatória.")
    @Size(max = 500, message = "A biografia deve ter no máximo 500 caracteres.")
    private String profileBio;

    private String profilePicturePath;

    // Getters e setters
    public String getProfileBio() {
        return profileBio;
    }

    public void setProfileBio(String profileBio) {
        this.profileBio = profileBio;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }
}
