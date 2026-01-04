package com.guillermo.mvsc.items.service;

import java.util.List;
import java.util.Optional;

import com.guillermo.mvsc.items.model.ItemDto;

public interface ItemService {
    List<ItemDto> findAll();

    Optional<ItemDto> findById(Long id );
}
