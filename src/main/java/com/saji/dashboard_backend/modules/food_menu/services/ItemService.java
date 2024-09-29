package com.saji.dashboard_backend.modules.food_menu.services;

import org.springframework.stereotype.Service;

import com.saji.dashboard_backend.modules.food_menu.dto.ItemDto;
import com.saji.dashboard_backend.modules.food_menu.dto.ItemResponse;
import com.saji.dashboard_backend.modules.food_menu.entites.Item;
import com.saji.dashboard_backend.modules.food_menu.entites.ItemSize;
import com.saji.dashboard_backend.modules.food_menu.repositories.ItemRepo;
import com.saji.dashboard_backend.shared.services.BaseService;

@Service
public class ItemService extends BaseService<Item, ItemDto, ItemResponse> {

    public ItemService(ItemRepo itemRepo, ItemMapper itemMapper) {
        super(itemRepo, itemMapper, Item.class);
    }

    @Override
    public void validate(Item object) {
        if(object.getImage().isEmpty()){
            throw new IllegalArgumentException("image sizes is required.");
            
        }        
        // check sizes
        // If an item has only one size, it should be named 'Standard' and have a non-zero price.
        if(object.getSizes() == null || object.getSizes().isEmpty()){
            throw new IllegalArgumentException("item sizes is required.");
        }
        
        if(object.getSizes().size() == 1){
            ItemSize size = object.getSizes().get(0);
            if(!size.getSize().equalsIgnoreCase("standard")){
                throw new IllegalArgumentException("If an item has only one size, it should be named 'Standard'");
            }
            if(size.getPrice() < 1){
                throw new IllegalArgumentException("itemSize price should be greater than zero.");
            }
        }
    }

}
