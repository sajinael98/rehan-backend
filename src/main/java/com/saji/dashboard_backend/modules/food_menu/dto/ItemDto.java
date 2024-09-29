package com.saji.dashboard_backend.modules.food_menu.dto;

import java.util.ArrayList;
import java.util.List;

import com.saji.dashboard_backend.modules.food_menu.entites.ItemSize;
import com.saji.dashboard_backend.shared.dtos.BaseRequest;
import com.saji.dashboard_backend.shared.dtos.BaseResponse;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemDto implements BaseRequest, BaseResponse {
    @NotEmpty
    private String title;

    @Size(min = 1)
    private String image;

    @NotNull
    private Long categoryId;

    private String description;

    private List<String> modifiers = new ArrayList<>();

    @NotEmpty
    private List<ItemSize> sizes  = new ArrayList<>();
}
