package com.guillermo.springcloud.msvc.produts.service;

import java.util.List;
import java.util.Optional;

import com.guillermo.springcloud.msvc.produts.entity.Product;

public interface ProductService {
	
	List<Product> findAll();

	Optional<Product> findById(Long id);
}
