package com.gildedrose.rest

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

import static org.springframework.http.HttpMethod.GET

@Component
class RestClient {

    private final TestRestTemplate restTemplate

    RestClient(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate
    }

    ResponseEntity<Map> get(String url) {
        def entity = new RequestEntity(GET, new URI(url))
        return restTemplate.exchange(entity, LinkedHashMap)
    }

}
