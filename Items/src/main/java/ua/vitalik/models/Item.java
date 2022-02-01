package ua.vitalik.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Item {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name = "Item Name";

    @NotEmpty(message = "Description should not be empty")
    @Size(min = 2, max = 200, message = "Description should be between 2 and 200 characters")
    private String description = "Item Description";

    @NotEmpty(message = "Image should not be empty")
    private String image = "https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png";


    public Item(int id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
