package com.loja.service.impl;

import com.loja.controller.dto.ItemsRequest;
import com.loja.controller.dto.ItemsResponse;
import com.loja.model.Items;
import com.loja.model.enums.StatusLogin;
import com.loja.repository.ItemRepository;
import com.loja.service.ItemService;
import com.loja.service.utils.RegisterItemsMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final RegisterItemsMapper mapper;
    @Autowired
    private ItemRepository repository;

    @Override
    @Transactional
    public ItemsResponse registerItem(ItemsRequest dto) {
        var register = savedItems(dto);
        register.getUser().setStatusCorp(StatusLogin.ACTIVE);
        var registerItemStore = this.repository.save(savedItems(dto));
        return this.mapper.toResponse(registerItemStore);
    }

    @Override
    @Transactional
    public List<ItemsResponse> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<Items> items = repository.findAll();
        return Arrays.asList(modelMapper.map(items, ItemsResponse[].class));
    }

    @Override
    public ResponseEntity<Optional<ItemsResponse>> changeItem(String id, ItemsRequest dto) {
        Optional<Items> items = repository.findById(id);
        if (items.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        items.ifPresent(s ->{
            s.setIdInternal(dto.getIdInternal());
            s.setNameItem(dto.getNameItem());
            s.setValueItem(dto.getValueItem());
            repository.save(s);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Items savedItems(ItemsRequest p){
        return Items.builder()
                .idInternal(p.getIdInternal())
                .nameItem(p.getNameItem())
                .valueItem(p.getValueItem())
                .user(p.getUser())
                .build();
    }

    public ItemServiceImpl(RegisterItemsMapper mapper) {
        this.mapper = mapper;
    }
}
