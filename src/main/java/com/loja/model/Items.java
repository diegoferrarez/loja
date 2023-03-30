package com.loja.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Builder
@Document(collection = "itemsStore")
@NoArgsConstructor
@AllArgsConstructor
public class Items {

    @Id
    private String id;
    private int idInternal;
    private String nameItem;
    private BigDecimal valueItem;
    private UserAuth user;

}
