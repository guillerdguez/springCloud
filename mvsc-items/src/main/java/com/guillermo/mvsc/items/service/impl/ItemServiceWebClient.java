package com.guillermo.mvsc.items.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.guillermo.mvsc.items.model.ItemDto;
import com.guillermo.mvsc.items.model.ProductDto;
import com.guillermo.mvsc.items.service.ItemService;

@Primary
@Service
public class ItemServiceWebClient implements ItemService {

    private final WebClient.Builder clientBuilder;

    public ItemServiceWebClient(WebClient.Builder clientBuilder) {
        this.clientBuilder = clientBuilder;
    }

    // .block() porque no es reactivo
    @Override
    public List<ItemDto> findAll() {
        return clientBuilder.build().get()

                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(ProductDto.class)
                .map(product -> new ItemDto(product, new Random().nextInt(10) + 1))
                .collectList().block();
    }

    // .blockOptional() porque no es reactivo
    @Override
    public Optional<ItemDto> findById(Long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);

        try {
            return Optional.of(clientBuilder.build().get()
            .uri("/{id}", params)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve().bodyToMono(ProductDto.class)
                    .map(product -> new ItemDto(product, new Random().nextInt(10) + 1)).block());
        } catch (WebClientResponseException e) {
            return Optional.empty();

        }

    }

}
