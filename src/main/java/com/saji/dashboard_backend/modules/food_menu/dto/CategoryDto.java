package com.saji.dashboard_backend.modules.food_menu.dto;

import com.saji.dashboard_backend.shared.dtos.BaseRequest;
import com.saji.dashboard_backend.shared.dtos.BaseResponse;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDto implements BaseRequest, BaseResponse {
    @NotEmpty
    private String title;

    private boolean enabled;
}
