package com.saji.dashboard_backend.shared.dtos;

import java.util.List;

import lombok.Data;

@Data
public class ListResponse<T extends BaseResponse> {
    private List<T> data;
    private Long total;
}