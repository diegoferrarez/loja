package com.loja.controller;

import com.loja.controller.dto.ItemsRequest;
import com.loja.controller.dto.ItemsResponse;
import com.loja.service.ItemService;
import com.loja.utils.ItensConstants;
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
    public ItemsResponse register(@RequestBody ItemsRequest request,
                                  @RequestHeader(name = ItensConstants.USER_SIGN_HEADER) String user,
                                  @RequestHeader(name = ItensConstants.USER_NUMBER_CORP) String numberCorp){
        return itemsService.registerItem(user, numberCorp, request);
    }

    @GetMapping("find")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ItemsResponse> getall(){
        return itemsService.findAll();
    }

    @PutMapping("update-item/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<ItemsResponse>> update(@PathVariable String id,
                                                          @RequestBody ItemsRequest request,
                                                          @RequestHeader(name = ItensConstants.USER_SIGN_HEADER) String user,
                                                          @RequestHeader(name = ItensConstants.USER_NUMBER_CORP) String numberCorp){
        return itemsService.changeItem(id, request, user, numberCorp);
    }

    @DeleteMapping("delete-item/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteItem(@PathVariable final String id,
                             @RequestHeader(name = ItensConstants.USER_SIGN_HEADER) String user,
                             @RequestHeader(name = ItensConstants.USER_NUMBER_CORP) String numberCorp){
        return itemsService.delete(id, user, numberCorp);
    }

    @GetMapping("teste/{id}")
    public Long teste(@PathVariable Long id){
        return id;
    }
}
