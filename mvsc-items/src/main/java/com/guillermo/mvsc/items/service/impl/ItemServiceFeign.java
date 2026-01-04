package com.guillermo.mvsc.items.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.guillermo.mvsc.items.client.ProductFeignClient;
import com.guillermo.mvsc.items.model.ItemDto;
import com.guillermo.mvsc.items.model.ProductDto;
import com.guillermo.mvsc.items.service.ItemService;

import feign.FeignException;

@Service
public class ItemServiceFeign implements ItemService {

    private final ProductFeignClient productFeignClient;

    public ItemServiceFeign(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    @Override
    public List<ItemDto> findAll() {
        return productFeignClient.list().stream()
                .map(product -> new ItemDto(product, new Random().nextInt(10) + 1)).collect(Collectors.toList());
    }

    @Override
    public Optional<ItemDto> findById(Long id) {
        // revisar manejo de errores
        try {
            ProductDto product = productFeignClient.detail(id);
            return Optional.of(new ItemDto(product, new Random().nextInt(10) + 1));

        } catch (FeignException e) {
            return Optional.empty();
        }

    }

}
