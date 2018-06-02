package com.gildedrose.rest;

import com.gildedrose.Item;

import javax.persistence.*;

@Entity
public class ItemEntity {

    public ItemEntity() {
    }

    public ItemEntity(String name, int sellIn, int quality) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public static ItemEntity toItemEntity(Item item) {
        return new ItemEntity(item.name, item.sellIn, item.quality);
    }
}
