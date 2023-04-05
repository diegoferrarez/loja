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
import org.springframework.web.bind.annotation.DeleteMapping;

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
    public ItemsResponse registerItem(String user, String numberCorp, ItemsRequest dto){
        var register = savedItems(dto);
        register.getUser().setLogin(user);
        register.getUser().setNumberCorp(numberCorp);
        register.getUser().setStatusCorp(StatusLogin.ACTIVE);
        var registerItemStore = this.repository.save(savedItems(dto));
        log.info("Produto registrado por: " + dto.getUser().getLogin() + "| Number Corp: " + dto.getUser().getNumberCorp());
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
    @Transactional
    public ResponseEntity<Optional<ItemsResponse>> changeItem(String id, ItemsRequest dto, String user, String numberCorp) {
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
        log.info("Produto alterado por: " + user + "| Number Corp: " + numberCorp);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public String delete(final String id, String user, String numberCorp){
        repository.deleteById(id);
        log.info("Produto excluído da base por: " + user + "| Number Corp: " + numberCorp);
        return "succesfully deleted id: " + id;
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
