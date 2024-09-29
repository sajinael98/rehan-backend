package com.saji.dashboard_backend.modules.food_menu.dto;

import lombok.Data;

@Data
public class ItemResponse extends ItemDto {
    private Long id;
    private String category;
}
