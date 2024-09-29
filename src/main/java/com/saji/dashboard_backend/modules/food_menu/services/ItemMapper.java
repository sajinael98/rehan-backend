package com.saji.dashboard_backend.modules.food_menu.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saji.dashboard_backend.modules.food_menu.dto.ItemDto;
import com.saji.dashboard_backend.modules.food_menu.dto.ItemResponse;
import com.saji.dashboard_backend.modules.food_menu.entites.Category;
import com.saji.dashboard_backend.modules.food_menu.entites.Item;
import com.saji.dashboard_backend.modules.food_menu.repositories.CategoryRepo;
import com.saji.dashboard_backend.shared.dtos.BaseRequest;
import com.saji.dashboard_backend.shared.dtos.BaseResponse;
import com.saji.dashboard_backend.shared.mappers.BaseMapper;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemMapper implements BaseMapper<Item> {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public BaseResponse convertEntityToResponse(Item entity) {
        ItemResponse res = new ItemResponse();
        BeanUtils.copyProperties(entity, res);
        Category category = entity.getCategory();
        res.setCategoryId(category.getId());
        res.setCategory(category.getTitle());
        return res;
    }

    @Override
    public Item convertRequestToEntity(Item entity, BaseRequest req) {
        ItemDto itemReq = (ItemDto) req;
        Category category = categoryRepo.findById(itemReq.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Category with id: " + itemReq.getCategoryId() + " is not found."));
        Item item = BaseMapper.super.convertRequestToEntity(entity, itemReq);
        item.setCategory(category);
        category.getItems().add(item);
        categoryRepo.save(category);
        return item;
    }

}
