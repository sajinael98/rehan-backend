package com.saji.dashboard_backend.modules.food_menu.entites;

import java.util.List;

import com.saji.dashboard_backend.shared.entites.BaseEntity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "items")
@Data
@ToString(exclude = "category")
public class Item extends BaseEntity {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ElementCollection
    @CollectionTable(name = "item_modifiers", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "modifier")
    private List<String> modifiers;
    
    @ElementCollection
    @CollectionTable(name = "item_sizes", joinColumns = @JoinColumn(name = "item_id"))
    private List<ItemSize> sizes;
}
