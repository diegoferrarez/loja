package com.loja.model;

import com.loja.model.enums.StatusLogin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {

    private String login;
    private String numberCorp;
    private StatusLogin statusCorp;

}
