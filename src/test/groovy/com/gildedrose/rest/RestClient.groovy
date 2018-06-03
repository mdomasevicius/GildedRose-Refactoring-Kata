package com.gildedrose.rest

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

import static org.springframework.http.HttpMethod.DELETE
import static org.springframework.http.HttpMethod.GET
import static org.springframework.http.HttpMethod.POST
import static org.springframework.http.HttpMethod.PUT

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

    ResponseEntity<Map> post(String url, Object body) {
        def entity = new RequestEntity(body, POST, new URI(url))
        return restTemplate.exchange(entity, LinkedHashMap)
    }

    ResponseEntity<Map> put(String url, Object body) {
        def entity = new RequestEntity(body, PUT, new URI(url))
        return restTemplate.exchange(entity, LinkedHashMap)
    }

    ResponseEntity<Map> delete(String url) {
        def entity = new RequestEntity(DELETE, new URI(url))
        return restTemplate.exchange(entity, LinkedHashMap)
    }
}
