package com.saji.dashboard_backend.modules.food_menu.services;

import org.springframework.stereotype.Service;

import com.saji.dashboard_backend.modules.food_menu.dto.CategoryDto;
import com.saji.dashboard_backend.modules.food_menu.dto.CategoryResponse;
import com.saji.dashboard_backend.modules.food_menu.entites.Category;
import com.saji.dashboard_backend.shared.repositories.BaseRepository;
import com.saji.dashboard_backend.shared.services.BaseService;

@Service
public class CategoryService extends BaseService<Category, CategoryDto, CategoryResponse> {

    public CategoryService(BaseRepository<Category, Long> repo, CategoryMapper mapper) {
        super(repo, mapper, Category.class);
    }

}
