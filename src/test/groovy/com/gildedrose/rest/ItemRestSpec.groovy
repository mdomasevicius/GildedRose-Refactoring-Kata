package com.gildedrose.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import static org.springframework.http.HttpStatus.OK

@SpringBootTest(classes = Application, webEnvironment = WebEnvironment.RANDOM_PORT)
class ItemRestSpec extends Specification {

    @Autowired
    RestClient rest

    @Autowired
    TestRestTemplate restTemplate

    def 'can fetch item list'() {
        when:
            def response = rest.get('/api/items')
        then:
            response.statusCode == OK
            with(itemsFromResponse(response).find()) {
                name != null
                sellIn == null
                quality == null
            }
    }

    def 'can fetch item details'() {
        given:
            Long itemId = findItemId()
        when:
            def response = rest.get("/api/items/$itemId")
        then:
            response.statusCode == OK
            with(response.body) {
                name != null
                sellIn != null
                quality != null
            }
    }

    private Long findItemId() {
        return rest.get('/api/items').body
            ._embedded.items
            .find()
            ._links.self.href.split('/')
            .last() as Long
    }

    private static List itemsFromResponse(ResponseEntity<Map> response) {
        return response.body._embedded.items
    }
}
