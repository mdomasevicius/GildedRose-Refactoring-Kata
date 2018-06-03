package com.gildedrose.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(
    collectionResourceRel = "item",
    path = "items",
    excerptProjection = ItemEntity.ListProjection.class)
interface ItemRepo extends PagingAndSortingRepository<ItemEntity, Long> {

    @RestResource
    @Override
    Optional<ItemEntity> findById(Long aLong);

    @RestResource
    @Override
    Iterable<ItemEntity> findAll();

    @RestResource
    @Override
    Page<ItemEntity> findAll(Pageable pageable);

    @RestResource
    @Override
    Iterable<ItemEntity> findAll(Sort sort);
}
