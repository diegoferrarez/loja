package com.loja.controller.dto;

import com.loja.model.Items;
import com.loja.model.UserAuth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemsResponse {

    private String id;
    private int idInternal;
    private String nameItem;
    private BigDecimal valueItem;
    private UserAuth user;

    public static ItemsResponse converter(Items c){
        return ItemsResponse.builder()
                .id(c.getId())
                .idInternal(c.getIdInternal())
                .nameItem(c.getNameItem())
                .valueItem(c.getValueItem())
                .user(c.getUser())
                .build();
    }

}
