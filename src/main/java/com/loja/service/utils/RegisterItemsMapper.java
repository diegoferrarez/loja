package com.loja.service.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loja.controller.dto.ItemsResponse;
import com.loja.model.Items;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterItemsMapper {

    private final ObjectMapper mapper;

    @SneakyThrows
    public ItemsResponse toResponse(Items items){
        var json = this.mapper.writeValueAsString(items);
        return this.mapper.readValue(json, ItemsResponse.class);
    }
}
