package br.edu.bsi.trocaverso.dto;

import java.util.UUID;

public class ItemResponseDTO {
    private UUID id;
    private String itemName;
    private String itemDescription;
    private String itemPicturePath;
    private UUID ownerId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }
}
