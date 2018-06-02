package com.gildedrose.rest;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static java.util.stream.Collectors.toList;

@Component
class DataInit implements InitializingBean {

    private final ItemRepo repo;

    @Value("${gilded-rose.days-passed:10}")
    private Long daysPassed;

    DataInit(ItemRepo repo) {
        this.repo = repo;
    }

    @Override
    public void afterPropertiesSet() {
        GildedRose gildedRose = new GildedRose(new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Conjured Mana Cake", 3, 6)
        });

        for (int i = 0; i < daysPassed; i++) {
            gildedRose.updateQuality();
        }

        repo.saveAll(Arrays.stream(gildedRose.getItems()).map(ItemEntity::toItemEntity).collect(toList()));
    }

}
