package com.luzejc.bee_raves_api.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserUpdateDTO {
    private String username;

    @Email(message = "formato del email no valido")
    private String email;

    private String password;
}
