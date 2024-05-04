package com.example.utcckitchen.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class MenuDto {

    @NotEmpty(message = "The name is required")
    private String name;
    @Min(0)
    private double price;
    @Size(min = 10,message = "ต้องมากกว่า10ตัว")
    @Size(min = 2000,message = "ต้องไม่เกิน2000ตัว")
    private String description;
    private MultipartFile imageFile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}
