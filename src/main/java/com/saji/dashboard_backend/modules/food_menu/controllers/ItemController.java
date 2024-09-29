package com.saji.dashboard_backend.modules.food_menu.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saji.dashboard_backend.modules.food_menu.dto.ItemDto;
import com.saji.dashboard_backend.modules.food_menu.dto.ItemResponse;
import com.saji.dashboard_backend.modules.food_menu.entites.Item;
import com.saji.dashboard_backend.modules.food_menu.services.ItemService;
import com.saji.dashboard_backend.shared.controllers.BaseController;

@RestController
@RequestMapping("/items")
public class ItemController extends BaseController<Item, ItemDto, ItemResponse> {
     public ItemController(ItemService service) {
        super(service);
    }
}
