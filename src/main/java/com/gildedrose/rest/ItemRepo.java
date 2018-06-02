package com.gildedrose.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "item", path = "items")
interface ItemRepo extends PagingAndSortingRepository<ItemEntity, Long> {
}
