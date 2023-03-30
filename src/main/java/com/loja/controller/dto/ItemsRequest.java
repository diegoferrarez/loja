package com.loja.controller.dto;

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
public class ItemsRequest {

    private String id;
    private int idInternal;
    private String nameItem;
    private BigDecimal valueItem;
    private UserAuth user;

}
