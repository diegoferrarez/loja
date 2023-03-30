package com.loja.controller;

import com.loja.controller.dto.ItemsRequest;
import com.loja.controller.dto.ItemsResponse;
import com.loja.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/register-items/")
public class StoreController {

    @Autowired
    private ItemService itemsService;

    @PostMapping("insert")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemsResponse register(@RequestBody ItemsRequest request){
        return itemsService.registerItem(request);
    }

    @GetMapping("find")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ItemsResponse> getall(){
        return itemsService.findAll();
    }

    @PutMapping("update-item/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<ItemsResponse>> update(@PathVariable String id, @RequestBody ItemsRequest request){
        return itemsService.changeItem(id, request);
    }
}
