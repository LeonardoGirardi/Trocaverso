package br.edu.bsi.trocaverso.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user_profile")
public class UserProfile extends GenericModel {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userAccount;

    @Column(name = "profile_bio", nullable = false)
    private String profileBio;

    @Column(name = "profile_picture_path")
    private String profilePicturePath;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Item> itemsForTrade = new ArrayList<>();

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<ServiceOffer> servicesForTrade = new ArrayList<>();

    public User getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(User userAccount) {
        this.userAccount = userAccount;
    }

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

    public List<Item> getItemsForTrade() {
        return itemsForTrade;
    }

    public void setItemsForTrade(List<Item> itemsForTrade) {
        this.itemsForTrade = itemsForTrade;
    }

    public List<ServiceOffer> getServicesForTrade() {
        return servicesForTrade;
    }

    public void setServicesForTrade(List<ServiceOffer> servicesForTrade) {
        this.servicesForTrade = servicesForTrade;
    }
}
