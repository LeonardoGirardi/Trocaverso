package br.edu.bsi.trocaverso.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class UserRequestDTO {

    @NotBlank
    private  String userName;

    @NotBlank
    @Email
    private String userEmail;

    @NotBlank
    private String userPassword;

    @NotNull
    private UUID locationId;

    public @NotBlank String getUserName() {
        return userName;
    }

    public void setUserName(@NotBlank String userName) {
        this.userName = userName;
    }

    public @NotBlank @Email String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(@NotBlank @Email String userEmail) {
        this.userEmail = userEmail;
    }

    public @NotBlank String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(@NotBlank String userPassword) {
        this.userPassword = userPassword;
    }

    public @NotNull UUID getLocationId() {
        return locationId;
    }

    public void setLocationId(@NotNull UUID locationId) {
        this.locationId = locationId;
    }
}
