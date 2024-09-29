package com.saji.dashboard_backend.modules.food_menu.entites;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ItemSize {
    private String size;
    private double price;
}
