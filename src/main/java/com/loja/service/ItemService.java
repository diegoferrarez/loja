package com.loja.service;

import com.loja.controller.dto.ItemsRequest;
import com.loja.controller.dto.ItemsResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    ItemsResponse registerItem(ItemsRequest request);
    List<ItemsResponse> findAll();
    ResponseEntity<Optional<ItemsResponse>> changeItem(String id, ItemsRequest request);
}
