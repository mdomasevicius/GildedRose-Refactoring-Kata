package com.gildedrose;

import java.util.*;
import java.util.function.Consumer;

import static java.util.Arrays.*;
import static java.util.Optional.*;

/*
    Since requirments allow me to only change GildedRose#upadeQuality() I will
    refrain from changing contract e.g. Item[] to List<Item>
 */
class GildedRose {

    private Map<String, Consumer<Item>> itemRules = new HashMap<>();

    {
        itemRules.put(
                "Aged Brie",
                item -> {
                    if (item.sellIn > 0) {
                        item.quality += 1;
                    } else {
                        item.quality += 2;
                    }

                    item.quality = item.quality > 50 ? 50 : item.quality;

                    item.sellIn -= 1;
                });

        itemRules.put("Sulfuras, Hand of Ragnaros", item -> item.sellIn -= 1);
        itemRules.put("Conjured Mana Cake", item -> {
            if (item.sellIn > 0) {
                item.quality -= 2;
            } else {
                item.quality -= 4;
            }

            item.quality = item.quality < 0 ? 0 : item.quality;

            item.sellIn -= 1;
        });
        itemRules.put("Backstage passes to a TAFKAL80ETC concert", item -> {
            if (item.sellIn > 10) {
                item.quality += 1;
            } else if (item.sellIn > 5) {
                item.quality += 2;
            } else if (item.sellIn > 0) {
                item.quality += 3;
            }

            item.quality = item.sellIn < 1 ? 0 : item.quality;

            item.sellIn -= 1;
        });
    }

    private Consumer<Item> fallbackRule = item -> {
        if (item.sellIn > 0) {
            item.quality -= 1;
        } else {
            item.quality -= 2;
        }
        item.quality = item.quality < 0 ? 0 : item.quality;

        item.sellIn -= 1;
    };

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        stream(items).forEach(item ->
                ofNullable(itemRules.get(item.name)).orElse(fallbackRule).accept(item));
    }

}
