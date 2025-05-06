package br.edu.bsi.trocaverso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_location")
public class Location extends GenericModel {

    @Column(name = "location_city", nullable = false)
    private String city;

    @Column(name = "location_state", nullable = false)
    private String state;

    @Column(name = "location_country", nullable = false)
    private String country;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
