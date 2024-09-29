package com.saji.dashboard_backend.modules.food_menu.repositories;

import org.springframework.stereotype.Repository;

import com.saji.dashboard_backend.modules.food_menu.entites.Item;
import com.saji.dashboard_backend.shared.repositories.BaseRepository;

@Repository
public interface ItemRepo extends BaseRepository<Item, Long> {
}
