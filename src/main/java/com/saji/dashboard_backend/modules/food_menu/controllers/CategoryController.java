package com.saji.dashboard_backend.modules.food_menu.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saji.dashboard_backend.modules.food_menu.dto.CategoryDto;
import com.saji.dashboard_backend.modules.food_menu.dto.CategoryResponse;
import com.saji.dashboard_backend.modules.food_menu.entites.Category;
import com.saji.dashboard_backend.modules.food_menu.services.CategoryService;
import com.saji.dashboard_backend.shared.controllers.BaseController;

@RestController
@RequestMapping("/categories")
public class CategoryController extends BaseController<Category, CategoryDto, CategoryResponse>{
    public CategoryController(CategoryService service) {
        super(service);
    }
}
