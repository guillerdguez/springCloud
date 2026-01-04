package com.guillermo.springcloud.msvc.produts.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guillermo.springcloud.msvc.produts.entity.Product;
import com.guillermo.springcloud.msvc.produts.repository.ProductoRepository;
import com.guillermo.springcloud.msvc.produts.service.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductoRepository productoRepository;
    private final Environment environment;

    // paso previo a eureka
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return ((List<Product>) productoRepository.findAll()).stream().map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return productoRepository.findById(id).map(product -> {

            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;

        });
    }
}
