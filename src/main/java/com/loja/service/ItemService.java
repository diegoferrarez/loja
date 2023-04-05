package com.loja.service;

import com.loja.controller.dto.ItemsRequest;
import com.loja.controller.dto.ItemsResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    ItemsResponse registerItem(String user, String numberCorp, ItemsRequest request);
    List<ItemsResponse> findAll();
    ResponseEntity<Optional<ItemsResponse>> changeItem(String id, ItemsRequest request, String user, String numberCorp);
    String delete(String id, String user, String numberCorp);
}
