package br.edu.bsi.trocaverso.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_item")
public class Item extends GenericModel {

    @Column(name =  "item_name", nullable = false)
    private String itemName;

    @Column(name = "item_description", nullable = false)
    private String itemDescription;

    @Column(name = "item_picture_path")
    private String itemPicturePath;
    
    @ManyToOne
    @JoinColumn(name = "item_owner", nullable = false)
    private UserProfile owner;

    public UserProfile getOwner() {
        return owner;
    }

    public void setOwner(UserProfile owner) {
        this.owner = owner;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemPicturePath() {
        return itemPicturePath;
    }

    public void setItemPicturePath(String itemPicturePath) {
        this.itemPicturePath = itemPicturePath;
    }

}
