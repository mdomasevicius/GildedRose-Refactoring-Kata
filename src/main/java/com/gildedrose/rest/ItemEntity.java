package com.gildedrose.rest;

import com.gildedrose.Item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
class ItemEntity {

    ItemEntity() {
    }

    private ItemEntity(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int sellIn;

    private int quality;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public int getQuality() {
        return quality;
    }

    static ItemEntity toItemEntity(Item item) {
        return new ItemEntity(item.name, item.sellIn, item.quality);
    }

    interface ListProjection {
        String getName();
    }
}
