package com.guillermo.mvsc.items.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.guillermo.mvsc.items.model.ProductDto;
@FeignClient(name = "msvc-products" )
public interface ProductFeignClient {
    
    @GetMapping
    List<ProductDto> list();

    @GetMapping("/{id}")
    ProductDto detail(@PathVariable Long id);
    
}
