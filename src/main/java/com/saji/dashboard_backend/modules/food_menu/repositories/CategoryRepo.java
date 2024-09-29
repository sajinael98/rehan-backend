package com.saji.dashboard_backend.modules.food_menu.repositories;

import org.springframework.stereotype.Repository;

import com.saji.dashboard_backend.modules.food_menu.entites.Category;
import com.saji.dashboard_backend.shared.repositories.BaseRepository;

@Repository
public interface CategoryRepo extends BaseRepository<Category, Long> {

}
