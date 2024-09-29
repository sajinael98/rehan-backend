package com.saji.dashboard_backend.modules.food_menu.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.saji.dashboard_backend.modules.food_menu.dto.CategoryResponse;
import com.saji.dashboard_backend.modules.food_menu.entites.Category;
import com.saji.dashboard_backend.shared.dtos.BaseResponse;
import com.saji.dashboard_backend.shared.mappers.BaseMapper;

@Service
public class CategoryMapper implements BaseMapper<Category> {

    @Override
    public BaseResponse convertEntityToResponse(Category entity) {
      CategoryResponse res = new CategoryResponse();
      BeanUtils.copyProperties(entity, res);
      return res;
    }
    
}
