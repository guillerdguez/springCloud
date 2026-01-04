package com.guillermo.springcloud.msvc.produts.repository;

import org.springframework.data.repository.CrudRepository;

import com.guillermo.springcloud.msvc.produts.entity.Product;

public interface ProductoRepository extends CrudRepository<Product, Long> {

}
