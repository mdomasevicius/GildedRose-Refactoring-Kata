package com.gildedrose

import spock.lang.Specification
import spock.lang.Unroll

class GildedRoseSpec extends Specification {

    @Unroll('aged brie quality should be #expectedQuality after #days days')
    def 'Aged Brie" actually increases in Quality the older it gets'() {
        given:
            GildedRose app = new GildedRose(
                [new Item('Aged Brie', sellIn, 0)] as Item[]
            )

        expect:
            days.times { app.updateQuality() }
            app.items.find().quality == expectedQuality

        where:
            days | sellIn || expectedQuality
            1    | 1      || 1
            2    | 0      || 4
            3    | 10     || 3
            4    | 2      || 6
            5    | 0      || 10
            6    | 0      || 12
            100  | 0      || 50 // The Quality of an item is never more than 50
            6    | 6      || 6
            6    | 5      || 7
            10   | 4      || 16
    }

    @Unroll
    def 'random item after #days days with sellIn #sellIn and quality #quality is expected to degrade to #expectedQuality'() {
        given:
            GildedRose app = new GildedRose(
                [new Item('Random Item', sellIn, quality)] as Item[]
            )

        expect:
            days.times { app.updateQuality() }
            app.items.find().quality == expectedQuality

        where:
            days | sellIn | quality || expectedQuality
            10   | 5      | 26      || 11
            10   | 26     | 26      || 16
            10   | 0      | 40      || 20
            100  | 0      | 20      || 0 // The Quality of an item is never negative

    }

    def '"Sulfuras", being a legendary item, never has to be sold or decreases in Quality'() {
        given:
            GildedRose app = new GildedRose(
                [new Item('Sulfuras, Hand of Ragnaros', 0, 50)] as Item[]
            )

        expect:
            days.times { app.updateQuality() }
            app.items.find().quality == expectedQuality

        where:
            days || expectedQuality
            1    || 50
            2    || 50
            3    || 50
            100  || 50
            1000 || 50
    }

    @Unroll('"Backstage passes" with sellIn (20) after days #days should be worth #expectedQuality')
    def '"Backstage passes", like aged brie, increases in Quality as its SellIn value approaches'() {
        given:
            GildedRose app = new GildedRose(
                [new Item('Backstage passes to a TAFKAL80ETC concert', 20, 0)] as Item[]
            )

        expect:
            days.times { app.updateQuality() }
            app.items.find().quality == expectedQuality

        where:
            days || expectedQuality
            1    || 1
            2    || 2
            3    || 3
            4    || 4
            5    || 5
            6    || 6
            7    || 7
            8    || 8
            9    || 9
            10   || 10
            11   || 12
            12   || 14
            13   || 16
            14   || 18
            15   || 20
            16   || 23
            17   || 26
            18   || 29
            19   || 32
            20   || 35 // day of event
            21   || 0
    }

    @Unroll('"Conjured" item with sellIn (10) quality (50) after days #days should be worth #expectedQuality')
    def '"Conjured" items degrade in Quality twice as fast as normal items'() {
        given:
            GildedRose app = new GildedRose(
                [new Item('Conjured Mana Cake', 10, 50)] as Item[]
            )

        expect:
            days.times { app.updateQuality() }
            app.items.find().quality == expectedQuality

        where:
            days || expectedQuality
            1    || 48
            2    || 46
            3    || 44
            4    || 42
            5    || 40
            6    || 38
            7    || 36
            8    || 34
            9    || 32
            10   || 30
            11   || 26
            12   || 22
            13   || 18
            14   || 14
            15   || 10
            16   || 6
            17   || 2
            18   || 0
            19   || 0
            20   || 0
    }
}
