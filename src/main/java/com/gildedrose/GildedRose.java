package com.gildedrose;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

/*
    Since requirements allow me to only change GildedRose#upadeQuality() I will
    refrain from changing contract e.g. Item[] to List<Item>
 */
public class GildedRose {

    private final Map<String, Consumer<Item>> qualityUpdateRules = new HashMap<>();

    private final Consumer<Item> defaultQualityRule = item -> {
        item.quality -= item.sellIn > 0 ? 1 : 2;
        item.quality = item.quality < 0 ? 0 : item.quality;
    };

    private final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;

        qualityUpdateRules.put("Aged Brie", item -> {
            item.quality += item.sellIn > 0 ? 1 : 2;
            item.quality = item.quality > 50 ? 50 : item.quality;
        });

        qualityUpdateRules.put("Sulfuras", item -> {/* do nothing */});

        qualityUpdateRules.put("Conjured", item -> {
            item.quality -= item.sellIn > 0 ? 2 : 4;
            item.quality = item.quality < 0 ? 0 : item.quality;
        });

        qualityUpdateRules.put("Backstage", item -> {
            if (item.sellIn > 10) {
                item.quality += 1;
            } else if (item.sellIn > 5) {
                item.quality += 2;
            } else if (item.sellIn > 0) {
                item.quality += 3;
            }

            item.quality = item.sellIn < 1 ? 0 : item.quality;
        });
    }

    public void updateQuality() {
        stream(items).forEach(item -> {
            findApplicableRuleOrFail(item).accept(item);
            decreaseSellIn(item);
        });
    }

    private Consumer<Item> findApplicableRuleOrFail(Item item) {
        List<Consumer<Item>> applicableRules = qualityUpdateRules.keySet()
                .stream()
                .filter(key -> nameContainsKey(item.name, key))
                .map(qualityUpdateRules::get)
                .collect(toList());

        if (applicableRules.size() > 1) {
            throw new IllegalArgumentException(
                "Conflict - multiple rules found for single item (" + item.toString() + ")");
        }

        return applicableRules.isEmpty() ? defaultQualityRule : applicableRules.get(0);
    }

    private static boolean nameContainsKey(String name, String key) {
        return name.toLowerCase().contains(key.toLowerCase());
    }

    private static void decreaseSellIn(Item item) {
        item.sellIn -= 1;
    }

    public Item[] getItems() {
        return items;
    }
}
