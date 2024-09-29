package com.saji.dashboard_backend.modules.food_menu.entites;

import java.util.ArrayList;
import java.util.List;

import com.saji.dashboard_backend.shared.entites.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category extends BaseEntity {
    @Column
    private String title;

    @Column
    private boolean enabled;

    @OneToMany(mappedBy = "category")
    private List<Item> items = new ArrayList<>();
}
