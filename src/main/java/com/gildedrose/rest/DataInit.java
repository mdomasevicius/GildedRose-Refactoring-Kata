package com.gildedrose.rest;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@Configuration
class DataInit implements InitializingBean {

    private final ItemRepo repo;

    @Value("${gilded-rose.days-passed:10}")
    private Long daysPassed;

    DataInit(ItemRepo repo) {
        this.repo = repo;
    }

    @Override
    public void afterPropertiesSet() {
        txOperation().perform();
    }

    interface TxOperation {
        void perform();
    }

    @Bean
    TxOperation txOperation() {
        return new TxOperation() {
            @Transactional
            @Override
            public void perform() {
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

                for (int i = 0; i < 20; i++) {
                    gildedRose.updateQuality();
                }

                List<ItemEntity> collect = Arrays.stream(gildedRose.getItems()).map(ItemEntity::toItemEntity).collect(toList());
                repo.saveAll(collect);
            }
        };
    }
}
