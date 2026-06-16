package com.luzejc.bee_raves_api.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;

    public LoginResponseDTO(String token){
        this.token = token;
    }
}
